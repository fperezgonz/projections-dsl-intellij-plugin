package solutions.sulfura.projectionsdslintellijplugin.references;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

public class ProjectionsDslReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(SimpleTypes.PROPERTY_NAME),
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