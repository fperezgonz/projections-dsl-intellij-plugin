// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import solutions.sulfura.projectionsdslintellijplugin.psi.impl.*;

public interface SimpleTypes {

  IElementType PROJECTION = new ProjectionsDslElementType("PROJECTION");
  IElementType PROJECTION_CONTENT = new ProjectionsDslElementType("PROJECTION_CONTENT");
  IElementType PROPERTY_NAME = new ProjectionsDslElementType("PROPERTY_NAME");

  IElementType FIELD_NAME = new ProjectionsDslTokenType("FIELD_NAME");
  IElementType PROJECTION_END = new ProjectionsDslTokenType("PROJECTION_END");
  IElementType PROJECTION_START = new ProjectionsDslTokenType("PROJECTION_START");
  IElementType SEPARATOR = new ProjectionsDslTokenType("SEPARATOR");
  IElementType SPACE = new ProjectionsDslTokenType("SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROJECTION) {
        return new ProjectionsDslProjectionImpl(node);
      }
      else if (type == PROJECTION_CONTENT) {
        return new ProjectionsDslProjectionContentImpl(node);
      }
      else if (type == PROPERTY_NAME) {
        return new ProjectionsDslPropertyNameImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
