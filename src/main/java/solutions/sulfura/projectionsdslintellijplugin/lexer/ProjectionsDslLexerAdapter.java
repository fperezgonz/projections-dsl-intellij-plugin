package solutions.sulfura.projectionsdslintellijplugin.lexer;

import com.intellij.lexer.FlexAdapter;

public class ProjectionsDslLexerAdapter extends FlexAdapter {
    public ProjectionsDslLexerAdapter() {
        super(new _ProjectionsDslLexer(null));
    }
}
