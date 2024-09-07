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
    return projectionRoot(b, l + 1);
  }

  /* ********************************************************** */
  // PROJECTION_START SPACE* projectionContent SPACE* (SEPARATOR SPACE*)? PROJECTION_END
  public static boolean projection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection")) return false;
    if (!nextTokenIs(b, PROJECTION_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROJECTION_START);
    r = r && projection_1(b, l + 1);
    r = r && projectionContent(b, l + 1);
    r = r && projection_3(b, l + 1);
    r = r && projection_4(b, l + 1);
    r = r && consumeToken(b, PROJECTION_END);
    exit_section_(b, m, PROJECTION, r);
    return r;
  }

  // SPACE*
  private static boolean projection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_1", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projection_3", c)) break;
    }
    return true;
  }

  // (SEPARATOR SPACE*)?
  private static boolean projection_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4")) return false;
    projection_4_0(b, l + 1);
    return true;
  }

  // SEPARATOR SPACE*
  private static boolean projection_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && projection_4_0_1(b, l + 1);
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

  /* ********************************************************** */
  // propertyName ( SPACE* (SEPARATOR|projection) SPACE* propertyName)* SPACE* (SEPARATOR? SPACE* projection)?
  public static boolean projectionContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent")) return false;
    if (!nextTokenIs(b, FIELD_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = propertyName(b, l + 1);
    r = r && projectionContent_1(b, l + 1);
    r = r && projectionContent_2(b, l + 1);
    r = r && projectionContent_3(b, l + 1);
    exit_section_(b, m, PROJECTION_CONTENT, r);
    return r;
  }

  // ( SPACE* (SEPARATOR|projection) SPACE* propertyName)*
  private static boolean projectionContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!projectionContent_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_1", c)) break;
    }
    return true;
  }

  // SPACE* (SEPARATOR|projection) SPACE* propertyName
  private static boolean projectionContent_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionContent_1_0_0(b, l + 1);
    r = r && projectionContent_1_0_1(b, l + 1);
    r = r && projectionContent_1_0_2(b, l + 1);
    r = r && propertyName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean projectionContent_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_1_0_0", c)) break;
    }
    return true;
  }

  // SEPARATOR|projection
  private static boolean projectionContent_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1_0_1")) return false;
    boolean r;
    r = consumeToken(b, SEPARATOR);
    if (!r) r = projection(b, l + 1);
    return r;
  }

  // SPACE*
  private static boolean projectionContent_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_1_0_2", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projectionContent_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_2", c)) break;
    }
    return true;
  }

  // (SEPARATOR? SPACE* projection)?
  private static boolean projectionContent_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_3")) return false;
    projectionContent_3_0(b, l + 1);
    return true;
  }

  // SEPARATOR? SPACE* projection
  private static boolean projectionContent_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionContent_3_0_0(b, l + 1);
    r = r && projectionContent_3_0_1(b, l + 1);
    r = r && projection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR?
  private static boolean projectionContent_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_3_0_0")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  // SPACE*
  private static boolean projectionContent_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_3_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SPACE* projection SPACE*
  static boolean projectionRoot(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot")) return false;
    if (!nextTokenIs(b, "", PROJECTION_START, SPACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionRoot_0(b, l + 1);
    r = r && projection(b, l + 1);
    r = r && projectionRoot_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean projectionRoot_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionRoot_0", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projectionRoot_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionRoot_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // FIELD_NAME
  public static boolean propertyName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyName")) return false;
    if (!nextTokenIs(b, FIELD_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FIELD_NAME);
    exit_section_(b, m, PROPERTY_NAME, r);
    return r;
  }

}
