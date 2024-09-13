package solutions.sulfura.projectionsdslintellijplugin.utils;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

import java.util.Arrays;
import java.util.List;

import static solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil.*;

public class ProjectionsDslCompletionContributor extends CompletionContributor {

    public ProjectionsDslCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SimpleTypes.FIELD_NAME),
                new ProjectionsDslCompletionProvider()
        );
    }

    static class ProjectionsDslCompletionProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {

            PsiElement element = parameters.getPosition();
            PsiElement originalElement = parameters.getOriginalPosition();
            PsiAnnotation psiAnnotation = getContextAnnotation(element);

            if (psiAnnotation == null) {
                return;
            }

            if (originalElement == null) {
                return;
            }

            List<String> projectionPropertyPath;
            projectionPropertyPath = getPathToProperty(originalElement);
            String inputtedTextForCurrentProperty = getInputtedTextForCurrentProperty(parameters);
            String searchTerm = ObjectUtils.firstNonNull(inputtedTextForCurrentProperty, "");
            projectionPropertyPath.add(searchTerm);
            PsiType psiType = getProjectedPsiType(psiAnnotation);

            if (!(psiType instanceof PsiClassType)) {
                return;
            }

            //Find The projected class for the path of the current element
            PsiClass psiNestedProjectionRootClass = ProjectionsDslUtil.findProjectedClassAtPath(psiAnnotation, projectionPropertyPath);

            if (psiNestedProjectionRootClass == null) {
                return;
            }

            //Find the field that corresponds to the current element and return it
            PsiField[] fields = psiNestedProjectionRootClass.getAllFields();

            //TODO filter out properties already declared in the current projection
            result.addAllElements(
                    Arrays.stream(fields)
                            .map(PsiField::getName)
                            .filter(field -> field.toLowerCase().contains(searchTerm.toLowerCase()))
                            .map(LookupElementBuilder::create)
                            .toList()
            );

        }

        public String getInputtedTextForCurrentProperty(@NotNull CompletionParameters parameters) {

            int cursorOffset = parameters.getOffset();
            PsiElement psiElement = parameters.getOriginalPosition();

            //If psiElement is null, it is pointless to go further
            if (psiElement == null) {
                return null;
            }

            /*The text being written right now for the current property*/
            return psiElement.getText().substring(0, cursorOffset - psiElement.getOriginalElement().getTextOffset());

        }

    }

}
