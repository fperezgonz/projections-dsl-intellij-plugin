package solutions.sulfura.projectionsdslintellijplugin.language;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class ProjectionDslRoot extends PsiFileBase {

    protected ProjectionDslRoot(@NotNull FileViewProvider viewProvider, @NotNull Language language) {
        super(viewProvider, language);
    }

    @Override
    public @NotNull FileType getFileType() {
        return ProjectionsDslLanguageFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "ProjectionDslRoot";
    }
}
