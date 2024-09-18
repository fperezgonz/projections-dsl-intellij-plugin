package solutions.sulfura.projectionsdslintellijplugin.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ProjectionsDslLanguageFileType extends LanguageFileType {

    Icon fileIcon = IconLoader.getIcon("icon-filetype-dpd.svg", ProjectionsDslLanguageFileType.class);

    public static final ProjectionsDslLanguageFileType INSTANCE = new ProjectionsDslLanguageFileType();

    public ProjectionsDslLanguageFileType() {
        super(ProjectionsDsl.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Dto projections dsl file";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Dto projections dsl language file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "dpd";
    }

    @Override
    public Icon getIcon() {
        return fileIcon;
    }
}
