package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.psi.tree.TokenSet;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

public class ProjectionsDslTokenSets {

    TokenSet SEPARATOR = TokenSet.create(SimpleTypes.SEPARATOR);
    TokenSet FIELD_NAME = TokenSet.create(SimpleTypes.FIELD_NAME);
    TokenSet SPACE = TokenSet.create(SimpleTypes.SPACE);

}
