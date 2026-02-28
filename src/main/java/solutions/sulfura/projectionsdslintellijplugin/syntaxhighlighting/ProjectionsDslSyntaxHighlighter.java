package solutions.sulfura.projectionsdslintellijplugin.syntaxhighlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.language.ProjectionsDslLexerAdapter;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ProjectionsDslSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey PROPERTY_NAME =
            createTextAttributesKey("PROPERTY_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey AS_KEYWORD =
            createTextAttributesKey("AS_KEYWORD", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey PROPERTY_ALIAS_LITERAL =
            createTextAttributesKey("PROPERTY_ALIAS_LITERAL", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey COLON =
            createTextAttributesKey("COLON", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
    public static final TextAttributesKey PROJECTION_TYPE_ALIAS_LITERAL =
            createTextAttributesKey("PROJECTION_TYPE_ALIAS_LITERAL", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey PROJECTION =
            createTextAttributesKey("PROJECTION");
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("PROJECTIONS_DSL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] PROPERTY_NAME_KEYS = new TextAttributesKey[]{PROPERTY_NAME};
    private static final TextAttributesKey[] PROPERTY_ALIAS_KEYS = new TextAttributesKey[]{PROPERTY_ALIAS_LITERAL};
    private static final TextAttributesKey[] TYPE_ALIAS_KEYS = new TextAttributesKey[]{PROJECTION_TYPE_ALIAS_LITERAL};
    private static final TextAttributesKey[] BRACES_KEYS = new TextAttributesKey[]{BRACES};
    private static final TextAttributesKey[] PROJECTION_KEYS = new TextAttributesKey[]{PROJECTION};
    private static final TextAttributesKey[] AS_KEYWORD_KEYS = new TextAttributesKey[]{AS_KEYWORD};
    private static final TextAttributesKey[] COLON_KEYS = new TextAttributesKey[]{COLON};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ProjectionsDslLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

        if (tokenType.equals(SimpleTypes.PROJECTION)) {
            return PROJECTION_KEYS;
        }
        if (tokenType.equals(SimpleTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        }
        if (tokenType.equals(SimpleTypes.PROPERTY_NAME)) {
            return PROPERTY_NAME_KEYS;
        }
        if (tokenType.equals(SimpleTypes.PROPERTY_ALIAS_LITERAL)) {
            return PROPERTY_ALIAS_KEYS;
        }
        if (tokenType.equals(SimpleTypes.PROJECTION_TYPE_ALIAS_LITERAL)) {
            return TYPE_ALIAS_KEYS;
        }
        if (tokenType.equals(SimpleTypes.PROJECTION_CONTAINER_START_CHAR) || tokenType.equals(SimpleTypes.PROJECTION_CONTAINER_END_CHAR)) {
            return BRACES_KEYS;
        }
        if (tokenType.equals(SimpleTypes.AS_KEYWORD)) {
            return AS_KEYWORD_KEYS;
        }
        if (tokenType.equals(SimpleTypes.COLON)) {
            return COLON_KEYS;
        }

        return EMPTY_KEYS;
    }
}
