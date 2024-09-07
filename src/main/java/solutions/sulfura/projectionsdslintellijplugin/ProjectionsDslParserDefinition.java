package solutions.sulfura.projectionsdslintellijplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.lexer.ProjectionsDslLexerAdapter;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslParser;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;

public class ProjectionsDslParserDefinition implements ParserDefinition {

    static IFileElementType INSTANCE = new IFileElementType(ProjectionsDsl.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ProjectionsDslLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new ProjectionsDslParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return INSTANCE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return SimpleTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ProjectionDslRoot(viewProvider, ProjectionsDsl.INSTANCE);
    }
}
