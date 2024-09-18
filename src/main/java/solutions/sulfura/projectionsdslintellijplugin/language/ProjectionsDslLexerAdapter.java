package solutions.sulfura.projectionsdslintellijplugin.language;

import com.intellij.lexer.FlexAdapter;
import solutions.sulfura.projectionsdslintellijplugin.lexer._ProjectionsDslLexer;

public class ProjectionsDslLexerAdapter extends FlexAdapter {
    public ProjectionsDslLexerAdapter() {
        super(new _ProjectionsDslLexer(null));
    }
}
