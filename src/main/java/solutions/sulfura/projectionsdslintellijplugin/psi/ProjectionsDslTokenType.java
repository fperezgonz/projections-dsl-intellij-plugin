package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.ProjectionsDsl;

public class ProjectionsDslTokenType extends IElementType {

    public ProjectionsDslTokenType(@NonNls @NotNull String debugName) {
        super(debugName, ProjectionsDsl.INSTANCE);
    }

    protected ProjectionsDslTokenType(@NonNls @NotNull String debugName, boolean register) {
        super(debugName, ProjectionsDsl.INSTANCE, register);
    }

}
