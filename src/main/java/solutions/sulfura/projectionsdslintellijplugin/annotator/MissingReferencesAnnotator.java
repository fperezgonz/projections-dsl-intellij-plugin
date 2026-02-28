package solutions.sulfura.projectionsdslintellijplugin.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.hyperkit.dsl.projections.DtoProjectionSpec;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslPropertyDecl;
import solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil;

public class MissingReferencesAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof ProjectionsDslPropertyDecl)) {
            return;
        }

        PsiAnnotation contextAnnotation = ProjectionsDslUtil.getContextAnnotation(element);
        if (contextAnnotation == null || !DtoProjectionSpec.class.getCanonicalName().equals(contextAnnotation.getQualifiedName())) {
            return;
        }

        PsiReference[] references = element.getReferences();

        if (references.length == 0) {
            return;
        }

        for (PsiReference reference : references) {
            if (reference.resolve() != null) {
                return;
            }
        }

        holder.newAnnotation(HighlightSeverity.WARNING, "Unresolved reference")
                .range(element)
                .create();
    }
}
