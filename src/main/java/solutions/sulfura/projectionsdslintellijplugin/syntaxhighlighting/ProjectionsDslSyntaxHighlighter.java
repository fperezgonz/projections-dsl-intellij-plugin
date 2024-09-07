package solutions.sulfura.projectionsdslintellijplugin.syntaxhighlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.lexer.ProjectionsDslLexerAdapter;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ProjectionsDslSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey FIELD_NAME =
            createTextAttributesKey("FIELD_NAME", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey LEAF_PROJECTION =
            createTextAttributesKey("LEAF_PROJECTION");
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("PROJECTIONS_DSL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] FIELD_NAME_KEYS = new TextAttributesKey[]{FIELD_NAME};
    private static final TextAttributesKey[] BRACES_KEYS = new TextAttributesKey[]{BRACES};
    private static final TextAttributesKey[] LEAF_PROJECTION_KEYS = new TextAttributesKey[]{LEAF_PROJECTION};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ProjectionsDslLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

        if(tokenType.equals(SimpleTypes.LEAF_PROJECTION)){
            return LEAF_PROJECTION_KEYS;
        }
        if (tokenType.equals(SimpleTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        }
        if (tokenType.equals(SimpleTypes.FIELD_NAME)) {
            return FIELD_NAME_KEYS;
        }
        if (tokenType.equals(SimpleTypes.PROJECTION_START) || tokenType.equals(SimpleTypes.PROJECTION_END)) {
            return BRACES_KEYS;
        }

        return EMPTY_KEYS;
    }
}
