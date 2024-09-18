package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDsl;

public class ProjectionsDslElementType extends IElementType {
    public ProjectionsDslElementType(@NonNls @NotNull String debugName) {
        super(debugName, ProjectionsDsl.INSTANCE);
    }

    protected ProjectionsDslElementType(@NonNls @NotNull String debugName, boolean register) {
        super(debugName, ProjectionsDsl.INSTANCE, register);
    }
}
