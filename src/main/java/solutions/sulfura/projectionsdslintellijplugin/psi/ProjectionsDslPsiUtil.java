package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.google.common.collect.Lists;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttribute;
import com.intellij.lang.jvm.annotation.JvmAnnotationClassValue;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.hyperkit.dtos.ListOperation;
import solutions.sulfura.hyperkit.dtos.ValueWrapper;

import java.util.*;

public class ProjectionsDslPsiUtil {

    @NotNull
    public static PsiReference[] getReferences(PsiElement thisElement) {
        //This is what makes the ProjectionsDslReferenceProvider work
        return com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry.getReferencesFromProviders(thisElement);
    }

    public static PsiAnnotation getContextAnnotation(@NotNull PsiElement element) {

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

    public static List<String> getPathToProperty(@NotNull PsiElement element) {

        List<String> path = new ArrayList<>();

        while (element != null) {

            element = findParentPropertyInOriginalElement(element);
            if (element != null) {
                path.add(element.getText());
            }

        }

        return Lists.reverse(path);

    }

    public static PsiElement findParentPropertyInOriginalElement(@NotNull PsiElement psiElement) {

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

            if (psiElement instanceof TreeElement && ((TreeElement) psiElement).getElementType() == SimpleTypes.PROPERTY_NAME) {
                return psiElement;
            }

        }

        return null;
    }

    public static PsiClass findClassForNestedProperty(@NotNull PsiClass psiProjectionRootClass, List<String> propertyPath, PsiType collectionPsiType) {

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

    public static PsiClassType getNestedDtoType(@NotNull PsiClassType psiClassType, PsiType collectionPsiType) {

        PsiClass psiClass = psiClassType.resolve();

        if (psiClass == null) {
            return null;
        }

        String qualifiedName = psiClass.getQualifiedName();

        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiClass.getProject());
        PsiClass dtoPsiClass = javaPsiFacade.findClass(solutions.sulfura.hyperkit.dtos.Dto.class.getCanonicalName(), GlobalSearchScope.allScope(psiClass.getProject()));

        if (dtoPsiClass == null) {
            //It is pointless, The project is not using gen-d
            return null;
        }

        //Found it!
        if (PsiTypesUtil.getClassType(dtoPsiClass).isAssignableFrom(psiClassType)) {
            return psiClassType;
        }

        //If it is nested within a known generic type, search for the Dto type in the parameterized type
        if (Objects.equals(qualifiedName, ValueWrapper.class.getCanonicalName())
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

    public static PsiType getProjectedPsiType(@NotNull PsiAnnotation psiAnnotation) {

        // Retrieve the type annotated by this PsiAnnotation
        PsiElement parent = psiAnnotation.getParent();
        PsiType psiType = null;

        //Try to find an attribute named projectedClass
        JvmAnnotationAttribute projectedClassAttribute = psiAnnotation.getAttributes().stream()
                .filter(attr -> attr.getAttributeName().equals("projectedClass"))
                .findFirst()
                .orElse(null);

        //If the projected class is defined in the annotation, return that class
        if (projectedClassAttribute != null && projectedClassAttribute.getAttributeValue() instanceof JvmAnnotationClassValue projectedClass
                && projectedClass.getClazz() instanceof PsiClass psiClass) {
            return PsiTypesUtil.getClassType(psiClass);
        }

        //If no projected class was specified on the annotation, try to infer it from the type annotated by this annotation
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

    public static PsiClass findProjectedClassAtPath(PsiAnnotation psiAnnotation, List<String> projectionPropertyPath) {

        PsiType psiType = getProjectedPsiType(psiAnnotation);

        if (!(psiType instanceof PsiClassType psiClassType)) {
            return null;
        }

        //Find The projected class for the root of the Dsl text
        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiAnnotation.getProject());
        PsiClass dtoPsiClass = javaPsiFacade.findClass(solutions.sulfura.hyperkit.dtos.Dto.class.getCanonicalName(), GlobalSearchScope.allScope(psiAnnotation.getProject()));

        if (dtoPsiClass == null || !PsiTypesUtil.getClassType(dtoPsiClass).isAssignableFrom(psiClassType)) {
            return null;
        }

        PsiClass psiProjectionRootClass = PsiUtil.resolveClassInType(psiType);

        if (psiProjectionRootClass == null) {
            return null;
        }

        //Find The projected class for the current element
        PsiClass collectionPsiClass = Objects.requireNonNull(JavaPsiFacade.getInstance(dtoPsiClass.getProject())
                .findClass(Collection.class.getCanonicalName(), psiClassType.getResolveScope()));
        PsiType collectionPsiType = PsiTypesUtil.getClassType(collectionPsiClass);

        return findClassForNestedProperty(psiProjectionRootClass, projectionPropertyPath, collectionPsiType);

    }
}
