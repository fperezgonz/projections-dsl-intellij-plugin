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
EOL = (\r\n)|\r|\n
WS = (\ |\t)+
COMMA = \,
COLON = :
PROPERTY_ALIAS_NAME = (`((\\`)|(\\\\)|[^`\\])*`)|([^`:\,\s\t\r\n\{\}]+)
TYPE_ALIAS_NAME = {PROPERTY_ALIAS_NAME}
PROJECTION_CONTAINER_START_CHAR = \{
PROJECTION_CONTAINER_END_CHAR = \}
PROPERTY_NAME = [^:\,\s\t\r\n\{\}]+
AS_KEYWORD = as|AS
%state AFTER_PROPERTY_ALIAS
%state PROJECTION_CONTAINER_START
%state AWAITING_PROPERTY_NAME
%state AWAITING_PROPERTY_ALIAS
%state AWAITING_TYPE_ALIAS_BEFORE_PROJECTION
%state AWAITING_TYPE_ALIAS_AFTER_PROJECTION
%state AFTER_PROPERTY_NAME
%state AFTER_PROJECTION_CONTAINER
%state AFTER_PROJECTION
%state AFTER_SEPARATOR


%%
// This is the starting state for the root projection
<YYINITIAL> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { return SPACE; }
  {PROPERTY_NAME}                       { yybegin(AFTER_PROPERTY_NAME); return PROPERTY_NAME; }
  {COLON}                               { yybegin(AWAITING_TYPE_ALIAS_BEFORE_PROJECTION); return COLON; }
  {PROJECTION_CONTAINER_START_CHAR}     { yybegin(AWAITING_PROPERTY_NAME); return PROJECTION_CONTAINER_START_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}

<AWAITING_TYPE_ALIAS_BEFORE_PROJECTION> {
  {WS}                                  { return SPACE; }
  {TYPE_ALIAS_NAME}                     { yybegin(PROJECTION_CONTAINER_START); return PROJECTION_TYPE_ALIAS_LITERAL; }
  [^]                                   { return BAD_CHARACTER; }
}

// After {
<PROJECTION_CONTAINER_START> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { return SPACE; }
  {PROJECTION_CONTAINER_START_CHAR}     { yybegin(AWAITING_PROPERTY_NAME); return PROJECTION_CONTAINER_START_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}

// A property name is the only meaningful token in this state
<AWAITING_PROPERTY_NAME> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { return SPACE; }
  {PROPERTY_NAME}                       { yybegin(AFTER_PROPERTY_NAME); return PROPERTY_NAME; }
  [^]                                   { return BAD_CHARACTER; }
}

<AFTER_PROPERTY_NAME> {
  {WS}                                  { return SPACE; }
  {AS_KEYWORD}                          { yybegin(AWAITING_PROPERTY_ALIAS); return AS_KEYWORD; }
  {PROPERTY_ALIAS_NAME}                        { yybegin(AFTER_PROPERTY_ALIAS); return PROPERTY_ALIAS_LITERAL; }
  {COLON}                               { yybegin(AWAITING_TYPE_ALIAS_BEFORE_PROJECTION); return COLON; }
  {EOL}                                 { yybegin(AFTER_SEPARATOR); return SEPARATOR; }
  {COMMA}                               { yybegin(AFTER_SEPARATOR); return SEPARATOR; }
  {PROJECTION_CONTAINER_START_CHAR}     { yybegin(AWAITING_PROPERTY_NAME); return PROJECTION_CONTAINER_START_CHAR;  }
  {PROJECTION_CONTAINER_END_CHAR}       { yybegin(AFTER_PROJECTION_CONTAINER); return PROJECTION_CONTAINER_END_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}

<AWAITING_PROPERTY_ALIAS> {
  {WS}                                  { return SPACE; }
  {PROPERTY_ALIAS_NAME}                 { yybegin(AFTER_PROPERTY_ALIAS); return PROPERTY_ALIAS_LITERAL; }
  [^]                                   { return BAD_CHARACTER; }
}

<AFTER_PROPERTY_ALIAS> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { yybegin(AFTER_SEPARATOR); return SEPARATOR; }
  {COMMA}                               { yybegin(AFTER_SEPARATOR); return SEPARATOR; }
  {COLON}                               { yybegin(AWAITING_TYPE_ALIAS_BEFORE_PROJECTION); return COLON; }
  {PROJECTION_CONTAINER_START_CHAR}     { yybegin(AWAITING_PROPERTY_NAME); return PROJECTION_CONTAINER_START_CHAR; }
  {PROJECTION_CONTAINER_END_CHAR}       { yybegin(AFTER_PROJECTION_CONTAINER); return PROJECTION_CONTAINER_END_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}

<AFTER_SEPARATOR> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { return SPACE; }
  {PROPERTY_NAME}                       { yybegin(AFTER_PROPERTY_NAME); return PROPERTY_NAME; }
  {PROJECTION_CONTAINER_END_CHAR}       { yybegin(AFTER_PROJECTION_CONTAINER); return PROJECTION_CONTAINER_END_CHAR; }
  [^]                                   { return BAD_CHARACTER; }

}

// After }
<AFTER_PROJECTION_CONTAINER> {
  {WS}                                  { return SPACE; }
  {COLON}                               { yybegin(AWAITING_TYPE_ALIAS_AFTER_PROJECTION); return COLON; }
  {EOL}                                 { yybegin(AFTER_PROJECTION); return SEPARATOR; }
  {COMMA}                               { yybegin(AFTER_PROJECTION); return SEPARATOR; }
  {PROJECTION_CONTAINER_END_CHAR}       { return PROJECTION_CONTAINER_END_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}

<AWAITING_TYPE_ALIAS_AFTER_PROJECTION> {
  {WS}                                  { return SPACE; }
  {TYPE_ALIAS_NAME}                     { yybegin(AFTER_PROJECTION); return PROJECTION_TYPE_ALIAS_LITERAL; }
  [^]                                   { return BAD_CHARACTER; }
}

// After type alias after projection
<AFTER_PROJECTION> {
  {WS}                                  { return SPACE; }
  {EOL}                                 { return SPACE; }
  {COMMA}                               { yybegin(AFTER_SEPARATOR); return SEPARATOR; }
  {PROPERTY_NAME}                       { yybegin(AFTER_PROPERTY_NAME); return PROPERTY_NAME; }
  {PROJECTION_CONTAINER_END_CHAR}       { yybegin(AFTER_PROJECTION_CONTAINER); return PROJECTION_CONTAINER_END_CHAR; }
  [^]                                   { return BAD_CHARACTER; }
}