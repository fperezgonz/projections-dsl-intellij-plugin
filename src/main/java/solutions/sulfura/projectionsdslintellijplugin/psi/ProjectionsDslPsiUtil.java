package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public class ProjectionsDslPsiUtil {

    @NotNull
    public static PsiReference[] getReferences(PsiElement thisElement) {
        //This is what makes the ProjectionsDslReferenceProvider work
        return com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry.getReferencesFromProviders(thisElement);
    }

}
