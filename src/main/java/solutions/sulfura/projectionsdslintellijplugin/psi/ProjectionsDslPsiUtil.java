package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.lang.ASTNode;

public class ProjectionsDslPsiUtil {

    public static String getFieldName(ProjectionsDslPropertyName element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.FIELD_NAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

}
