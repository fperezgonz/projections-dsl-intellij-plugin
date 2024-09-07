package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public class ProjectionsDsl extends Language {

    public static final ProjectionsDsl INSTANCE = new ProjectionsDsl();

    protected ProjectionsDsl() {
        super("DtoProjectionsDsl");
    }
}
