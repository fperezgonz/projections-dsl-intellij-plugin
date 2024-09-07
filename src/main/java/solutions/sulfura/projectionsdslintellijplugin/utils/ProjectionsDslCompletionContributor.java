package solutions.sulfura.projectionsdslintellijplugin.utils;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

import java.util.Arrays;

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

            PsiType psiType = getPsiType(psiAnnotation);

            //TODO get class from canonical text and retrieve class fields for completion
            if (psiType instanceof PsiClassType psiClassType) {

                JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(psiAnnotation.getProject());
                //TODO Use cloud.sulfura.Dto
                PsiClass dtoPsiClass = javaPsiFacade.findClass(TestClass.TestDto.class.getCanonicalName(), psiAnnotation.getResolveScope());

                if (dtoPsiClass == null) {
                    return;
                }

                if (PsiTypesUtil.getClassType(dtoPsiClass).isAssignableFrom(psiClassType)) {

                    PsiClass psiClass = PsiUtil.resolveClassInType(psiType);

                    if (psiClass == null) {
                        return;
                    }

                    PsiField[] fields = psiClass.getAllFields();
                    //TODO nested projections
                    result.addAllElements(
                            Arrays.stream(fields)
                                    .map(field -> LookupElementBuilder.create(field.getName()))
                                    .toList()
                    );

                }

            }

        }

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
