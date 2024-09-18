package solutions.sulfura.projectionsdslintellijplugin.language;

import com.intellij.lang.Language;

public class ProjectionsDsl extends Language {

    public static final ProjectionsDsl INSTANCE = new ProjectionsDsl();

    protected ProjectionsDsl() {
        super("DtoProjectionsDsl");
    }
}
