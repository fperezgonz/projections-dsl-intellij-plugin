// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.*;
import static solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslParserUtil.*;
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
  // PROJECTION_TYPE_ALIAS? SPACE* projectionContentContainer SPACE* (PROJECTION_TYPE_ALIAS)?
  public static boolean projection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROJECTION, "<projection>");
    r = projection_0(b, l + 1);
    r = r && projection_1(b, l + 1);
    r = r && projectionContentContainer(b, l + 1);
    r = r && projection_3(b, l + 1);
    r = r && projection_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PROJECTION_TYPE_ALIAS?
  private static boolean projection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_0")) return false;
    consumeToken(b, PROJECTION_TYPE_ALIAS);
    return true;
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

  // (PROJECTION_TYPE_ALIAS)?
  private static boolean projection_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projection_4")) return false;
    consumeToken(b, PROJECTION_TYPE_ALIAS);
    return true;
  }

  /* ********************************************************** */
  // (SPACE* propertyDecl SPACE* SEPARATOR)* (SPACE* propertyDecl )? SPACE*
  public static boolean projectionContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROJECTION_CONTENT, "<projection content>");
    r = projectionContent_0(b, l + 1);
    r = r && projectionContent_1(b, l + 1);
    r = r && projectionContent_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (SPACE* propertyDecl SPACE* SEPARATOR)*
  private static boolean projectionContent_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!projectionContent_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_0", c)) break;
    }
    return true;
  }

  // SPACE* propertyDecl SPACE* SEPARATOR
  private static boolean projectionContent_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionContent_0_0_0(b, l + 1);
    r = r && propertyDecl(b, l + 1);
    r = r && projectionContent_0_0_2(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean projectionContent_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_0_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_0_0_0", c)) break;
    }
    return true;
  }

  // SPACE*
  private static boolean projectionContent_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_0_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "projectionContent_0_0_2", c)) break;
    }
    return true;
  }

  // (SPACE* propertyDecl )?
  private static boolean projectionContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1")) return false;
    projectionContent_1_0(b, l + 1);
    return true;
  }

  // SPACE* propertyDecl
  private static boolean projectionContent_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContent_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionContent_1_0_0(b, l + 1);
    r = r && propertyDecl(b, l + 1);
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

  /* ********************************************************** */
  // PROJECTION_CONTAINER_START_CHAR projectionContent PROJECTION_CONTAINER_END_CHAR
  public static boolean projectionContentContainer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionContentContainer")) return false;
    if (!nextTokenIs(b, PROJECTION_CONTAINER_START_CHAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROJECTION_CONTAINER_START_CHAR);
    r = r && projectionContent(b, l + 1);
    r = r && consumeToken(b, PROJECTION_CONTAINER_END_CHAR);
    exit_section_(b, m, PROJECTION_CONTENT_CONTAINER, r);
    return r;
  }

  /* ********************************************************** */
  // SPACE* (projection|projectionContent) (SEPARATOR|SPACE)*
  static boolean projectionRoot(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = projectionRoot_0(b, l + 1);
    r = r && projectionRoot_1(b, l + 1);
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

  // projection|projectionContent
  private static boolean projectionRoot_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot_1")) return false;
    boolean r;
    r = projection(b, l + 1);
    if (!r) r = projectionContent(b, l + 1);
    return r;
  }

  // (SEPARATOR|SPACE)*
  private static boolean projectionRoot_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!projectionRoot_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "projectionRoot_2", c)) break;
    }
    return true;
  }

  // SEPARATOR|SPACE
  private static boolean projectionRoot_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "projectionRoot_2_0")) return false;
    boolean r;
    r = consumeToken(b, SEPARATOR);
    if (!r) r = consumeToken(b, SPACE);
    return r;
  }

  /* ********************************************************** */
  // PROPERTY_NAME SPACE* PROPERTY_ALIAS? SPACE* projection?
  public static boolean propertyDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyDecl")) return false;
    if (!nextTokenIs(b, PROPERTY_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROPERTY_NAME);
    r = r && propertyDecl_1(b, l + 1);
    r = r && propertyDecl_2(b, l + 1);
    r = r && propertyDecl_3(b, l + 1);
    r = r && propertyDecl_4(b, l + 1);
    exit_section_(b, m, PROPERTY_DECL, r);
    return r;
  }

  // SPACE*
  private static boolean propertyDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyDecl_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "propertyDecl_1", c)) break;
    }
    return true;
  }

  // PROPERTY_ALIAS?
  private static boolean propertyDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyDecl_2")) return false;
    consumeToken(b, PROPERTY_ALIAS);
    return true;
  }

  // SPACE*
  private static boolean propertyDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyDecl_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "propertyDecl_3", c)) break;
    }
    return true;
  }

  // projection?
  private static boolean propertyDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyDecl_4")) return false;
    projection(b, l + 1);
    return true;
  }

}
