// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ProjectionsDslParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return projection(b, l + 1);
  }

  /* ********************************************************** */
  // FIELD_NAME SPACE* leafProjection?
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    if (!nextTokenIs(b, FIELD_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FIELD_NAME);
    r = r && fieldDeclaration_1(b, l + 1);
    r = r && fieldDeclaration_2(b, l + 1);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  // SPACE*
  private static boolean fieldDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "fieldDeclaration_1", c)) break;
    }
    return true;
  }

  // leafProjection?
  private static boolean fieldDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_2")) return false;
    leafProjection(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PROJECTION_START SPACE* (FIELD_NAME SPACE*) (SEPARATOR FIELD_NAME SPACE*)* SPACE* SEPARATOR? SPACE* PROJECTION_END
  public static boolean leafProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection")) return false;
    if (!nextTokenIs(b, PROJECTION_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROJECTION_START);
    r = r && leafProjection_1(b, l + 1);
    r = r && leafProjection_2(b, l + 1);
    r = r && leafProjection_3(b, l + 1);
    r = r && leafProjection_4(b, l + 1);
    r = r && leafProjection_5(b, l + 1);
    r = r && leafProjection_6(b, l + 1);
    r = r && consumeToken(b, PROJECTION_END);
    exit_section_(b, m, LEAF_PROJECTION, r);
    return r;
  }

  // SPACE*
  private static boolean leafProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_1", c)) break;
    }
    return true;
  }

  // FIELD_NAME SPACE*
  private static boolean leafProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FIELD_NAME);
    r = r && leafProjection_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean leafProjection_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_2_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_2_1", c)) break;
    }
    return true;
  }

  // (SEPARATOR FIELD_NAME SPACE*)*
  private static boolean leafProjection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!leafProjection_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_3", c)) break;
    }
    return true;
  }

  // SEPARATOR FIELD_NAME SPACE*
  private static boolean leafProjection_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SEPARATOR, FIELD_NAME);
    r = r && leafProjection_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean leafProjection_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_3_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_3_0_2", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean leafProjection_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_4", c)) break;
    }
    return true;
  }

  // SEPARATOR?
  private static boolean leafProjection_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_5")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  // SPACE*
  private static boolean leafProjection_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leafProjection_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "leafProjection_6", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SPACE* PROJECTION_START SPACE* fieldDeclaration (SEPARATOR SPACE* fieldDeclaration )* SPACE* SEPARATOR? SPACE* PROJECTION_END SPACE*
  static boolean projection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection")) return false;
    if (!nextTokenIs(b, "", PROJECTION_START, SPACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projection_0(b, l + 1);
    r = r && consumeToken(b, PROJECTION_START);
    r = r && projection_2(b, l + 1);
    r = r && fieldDeclaration(b, l + 1);
    r = r && projection_4(b, l + 1);
    r = r && projection_5(b, l + 1);
    r = r && projection_6(b, l + 1);
    r = r && projection_7(b, l + 1);
    r = r && consumeToken(b, PROJECTION_END);
    r = r && projection_9(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean projection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_0", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_2", c)) break;
    }
    return true;
  }

  // (SEPARATOR SPACE* fieldDeclaration )*
  private static boolean projection_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!projection_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "projection_4", c)) break;
    }
    return true;
  }

  // SEPARATOR SPACE* fieldDeclaration
  private static boolean projection_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && projection_4_0_1(b, l + 1);
    r = r && fieldDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean projection_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_4_0_1", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projection_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_5", c)) break;
    }
    return true;
  }

  // SEPARATOR?
  private static boolean projection_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_6")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  // SPACE*
  private static boolean projection_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_7", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projection_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_9", c)) break;
    }
    return true;
  }

}
