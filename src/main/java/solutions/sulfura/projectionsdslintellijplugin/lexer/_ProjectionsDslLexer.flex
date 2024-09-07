package solutions.sulfura.projectionsdslintellijplugin.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.*;

%%

%{
  public _ProjectionsDslLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _ProjectionsDslLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

//EOL=\R
LINE_TERMINATOR = (\r\n)|\r|\n
WS = (\ |\t)+
COMMA = \,
PROJECTION_START = \{
PROJECTION_END = \}
FIELD_NAME = [^\,\s\t\r\n\{\}]+
%state AFTER_FIELD_NAME


%%

<YYINITIAL> {
  {WS}                                  { return SPACE; }
  {LINE_TERMINATOR}                     { return SPACE; }
  {COMMA}                               { return SEPARATOR; }
  {FIELD_NAME}                          { yybegin(AFTER_FIELD_NAME); return FIELD_NAME; }
  {PROJECTION_START}                    { return PROJECTION_START; }
  {PROJECTION_END}                      { return PROJECTION_END; }
  [^]                                   { return BAD_CHARACTER; }

}

<AFTER_FIELD_NAME> {
  {WS}                                  { return SPACE; }
  {FIELD_NAME}                          { return FIELD_NAME; }
  {LINE_TERMINATOR}                     { yybegin(YYINITIAL); return SEPARATOR; }
  {COMMA}                               { yybegin(YYINITIAL); return SEPARATOR; }
  {PROJECTION_START}                    { yybegin(YYINITIAL); return PROJECTION_START;  }
  {PROJECTION_END}                      { yybegin(YYINITIAL); return PROJECTION_END; }
  [^]                                   { return BAD_CHARACTER; }

}
