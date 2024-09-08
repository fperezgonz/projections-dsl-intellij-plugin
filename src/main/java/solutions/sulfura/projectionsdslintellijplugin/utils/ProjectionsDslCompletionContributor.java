package solutions.sulfura.projectionsdslintellijplugin.utils;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.ProcessingContext;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.gend.dtos.ListOperation;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslProjection;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslPropertyName;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

import java.util.*;

public class ProjectionsDslCompletionContributor extends CompletionContributor {


    public ProjectionsDslCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SimpleTypes.FIELD_NAME),
                new ProjectionsDslCompletionProvider()
        );
    }

    static class ProjectionsDslCompletionProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {

            PsiAnnotation psiAnnotation = getContextAnnotation(parameters);

            if (psiAnnotation == null) {
                return;
            }

            List<String> projectionPropertyPath = getPropertyPath(parameters);

            String searchTerm = projectionPropertyPath.isEmpty() ?
                    "" : projectionPropertyPath.remove(projectionPropertyPath.size() - 1).toLowerCase();

            PsiType psiType = getPsiType(psiAnnotation);

            //Get class from canonical text and retrieve class fields for completion
            if (psiType instanceof PsiClassType psiClassType) {

                JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiAnnotation.getProject());
                PsiClass dtoPsiClass = javaPsiFacade.findClass(solutions.sulfura.gend.dtos.Dto.class.getCanonicalName(), GlobalSearchScope.allScope(psiAnnotation.getProject()));

                if (dtoPsiClass == null) {
                    return;
                }

                if (PsiTypesUtil.getClassType(dtoPsiClass).isAssignableFrom(psiClassType)) {

                    PsiClass psiProjectionRootClass = PsiUtil.resolveClassInType(psiType);

                    if (psiProjectionRootClass == null) {
                        return;
                    }

                    PsiClass collectionPsiClass = Objects.requireNonNull(JavaPsiFacade.getInstance(dtoPsiClass.getProject())
                            .findClass(Collection.class.getCanonicalName(), psiClassType.getResolveScope()));
                    PsiType collectionPsiType = PsiTypesUtil.getClassType(collectionPsiClass);

                    PsiClass psiNestedProjectionRootClass = findClassForNestedProperty(psiProjectionRootClass, projectionPropertyPath, collectionPsiType);

                    if (psiNestedProjectionRootClass == null) {
                        return;
                    }

                    PsiField[] fields = psiNestedProjectionRootClass.getAllFields();

                    result.addAllElements(
                            Arrays.stream(fields)
                                    .map(PsiField::getName)
                                    .filter(field -> field.toLowerCase().contains(searchTerm))
                                    .map(LookupElementBuilder::create)
                                    .toList()
                    );

                }

            }

        }

        public PsiClass findClassForNestedProperty(@NotNull PsiClass psiProjectionRootClass, List<String> propertyPath, PsiType collectionPsiType) {

            PsiClass projectionClass = psiProjectionRootClass;

            for (String propertyName : propertyPath) {

                for (PsiField field : projectionClass.getAllFields()) {

                    if (field.getName().equals(propertyName)) {

                        projectionClass = null;

                        if (field.getType() instanceof PsiClassType psiClassType) {

                            projectionClass = PsiTypesUtil.getPsiClass(getNestedDtoType(psiClassType, collectionPsiType));

                            if (projectionClass == null) {
                                return null;
                            }

                        }

                        //Could not find the path
                        if (projectionClass == null) {
                            return null;
                        }

                    }

                }

            }

            return projectionClass;

        }

        public PsiClassType getNestedDtoType(@NotNull PsiClassType psiClassType, PsiType collectionPsiType) {

            PsiClass psiClass = psiClassType.resolve();

            if (psiClass == null) {
                return null;
            }

            String qualifiedName = psiClass.getQualifiedName();

            JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiClass.getProject());
            PsiClass dtoPsiClass = javaPsiFacade.findClass(solutions.sulfura.gend.dtos.Dto.class.getCanonicalName(), GlobalSearchScope.allScope(psiClass.getProject()));

            if (dtoPsiClass == null) {
                //It is pointless, The project is not using gen-d
                return null;
            }

            //Found it!
            if (PsiTypesUtil.getClassType(dtoPsiClass).isAssignableFrom(psiClassType)) {
                return psiClassType;
            }

            //If it is nested within a known generic type, search for the Dto type in the parameterized type
            if (Objects.equals(qualifiedName, Option.class.getCanonicalName())
                    || Objects.equals(qualifiedName, Optional.class.getCanonicalName())
                    || Objects.equals(qualifiedName, ListOperation.class.getCanonicalName())
                    || collectionPsiType.isAssignableFrom(psiClassType)) {

                PsiType parameter = psiClassType.getParameters()[0];

                if (parameter instanceof PsiClassType parameterClassType) {
                    return getNestedDtoType(parameterClassType, collectionPsiType);
                }

            }

            return null;

        }

        public List<String> getPropertyPath(@NotNull CompletionParameters parameters) {

            List<String> path = new ArrayList<>();

            int cursorOffset = parameters.getOffset();

            PsiElement psiElement = parameters.getOriginalPosition();

            //If psiElement is null, it is pointless to go further
            if (psiElement == null) {
                return path;
            }

            /*The text being written right now for the current property*/
            String inputText = psiElement.getText().substring(0, cursorOffset - psiElement.getOriginalElement().getTextOffset());
            path.add(inputText);

            while (psiElement != null) {

                psiElement = findParentProperty(psiElement);
                if (psiElement != null) {
                    path.add(psiElement.getText());
                }

            }

            return Lists.reverse(path);

        }


        public PsiElement findParentProperty(@NotNull PsiElement psiElement) {

            //Find parentProjection
            while (psiElement != null) {

                //Found!
                if (psiElement instanceof ProjectionsDslProjection) {
                    break;
                }

                psiElement = psiElement.getParent();

            }

            //Find the property name for this projection
            while (psiElement != null) {

                psiElement = psiElement.getPrevSibling();

                if (psiElement instanceof ProjectionsDslPropertyName) {
                    return psiElement;
                }

            }

            return null;
        }

/* This only works when there are parsing errors
        public PsiElement findParentProperty(@NotNull PsiElement psiElement) {

            //Find the projection start
            while (psiElement != null) {

                if (psiElement instanceof LeafPsiElement) {

                    IElementType elementType = ((LeafPsiElement) psiElement).getElementType();

                    if (elementType == SimpleTypes.PROJECTION_END) {
                        psiElement = skipAdjacentProjection(psiElement);
                        continue;
                    }

                    //Found!
                    if (elementType == SimpleTypes.PROJECTION_START) {
                        psiElement = psiElement.getPrevSibling();
                        break;
                    }

                    psiElement = psiElement.getPrevSibling();

                }
            }

            //Find the property name for this projection
            while (psiElement != null) {

                if (psiElement instanceof LeafPsiElement) {

                    IElementType elementType = ((LeafPsiElement) psiElement).getElementType();

                    if (elementType == SimpleTypes.FIELD_NAME) {
                        return psiElement;
                    }

                    psiElement = psiElement.getPrevSibling();

                }
            }

            return null;

        }

        public PsiElement skipAdjacentProjection(@NotNull PsiElement projectionEnd) {

            int nestingDepth = 0;

            PsiElement psiElement = projectionEnd;

            while (psiElement != null) {

                if (psiElement instanceof LeafPsiElement) {
                    IElementType elementType = ((LeafPsiElement) psiElement).getElementType();
                    if (elementType == SimpleTypes.PROJECTION_END) {
                        nestingDepth++;
                    } else if (elementType == SimpleTypes.PROJECTION_START) {
                        if (nestingDepth == 0) {
                            return psiElement.getPrevSibling();
                        } else {
                            nestingDepth--;
                        }
                    }
                    psiElement = psiElement.getPrevSibling();
                }
            }

            return null;

        }*/

        public PsiAnnotation getContextAnnotation(@NotNull CompletionParameters parameters) {

            PsiElement element = parameters.getPosition();
            PsiElement injectionHost = InjectedLanguageManager.getInstance(element.getProject()).getInjectionHost(element);

            //Without Context, it is not possible to infer completion candidates
            if (injectionHost == null) {
                return null;
            }

            while (injectionHost != null && !(injectionHost instanceof PsiAnnotation)) {
                injectionHost = injectionHost.getParent();
            }

            if (injectionHost instanceof PsiAnnotation psiAnnotation) {
                return psiAnnotation;
            }

            return null;

        }

        public PsiType getPsiType(@NotNull PsiAnnotation psiAnnotation) {

            // Retrieve the type annotated by this PsiAnnotation
            PsiElement parent = psiAnnotation.getParent();
            PsiType psiType = null;

            if (parent instanceof PsiModifierList) {
                PsiElement grandParent = parent.getParent();
                if (grandParent instanceof PsiField psiField) {
                    // The type of the field
                    psiType = psiField.getType();
                } else if (grandParent instanceof PsiParameter psiParameter) {
                    // The return type of the method
                    psiType = psiParameter.getType();
                } else if (grandParent instanceof PsiMethod psiMethod) {
                    // The return type of the method
                    psiType = psiMethod.getReturnType();
                } else if (grandParent instanceof PsiClass) {
                    //Ignore
                }
            } else if (parent instanceof PsiTypeElement psiTypeElement) {
                // Directly on a type element
                psiType = psiTypeElement.getType();
            } else {
                // Handle other cases, such as local variables or specific language elements
                System.err.println("Unhandled parent type: " + parent.getClass().getName());
            }

            return psiType;

        }

    }

}
