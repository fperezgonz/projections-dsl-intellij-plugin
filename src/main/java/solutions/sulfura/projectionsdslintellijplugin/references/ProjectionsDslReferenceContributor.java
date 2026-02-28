package solutions.sulfura.projectionsdslintellijplugin.references;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.PROPERTY_DECL;

public class ProjectionsDslReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(PROPERTY_DECL),
                new ProjectionsDslReferenceProvider()
        );

    }

    public static class ProjectionsDslReferenceProvider extends PsiReferenceProvider {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {

            return new PsiReference[]{new ProjectionsDslReference(element)};

        }
    }

}