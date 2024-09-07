// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import solutions.sulfura.projectionsdslintellijplugin.psi.impl.*;

public interface SimpleTypes {

  IElementType FIELD_DECLARATION = new ProjectionsDslElementType("FIELD_DECLARATION");
  IElementType LEAF_PROJECTION = new ProjectionsDslElementType("LEAF_PROJECTION");

  IElementType FIELD_NAME = new ProjectionsDslTokenType("FIELD_NAME");
  IElementType PROJECTION_END = new ProjectionsDslTokenType("PROJECTION_END");
  IElementType PROJECTION_START = new ProjectionsDslTokenType("PROJECTION_START");
  IElementType SEPARATOR = new ProjectionsDslTokenType("SEPARATOR");
  IElementType SPACE = new ProjectionsDslTokenType("SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == FIELD_DECLARATION) {
        return new ProjectionsDslFieldDeclarationImpl(node);
      }
      else if (type == LEAF_PROJECTION) {
        return new ProjectionsDslLeafProjectionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
