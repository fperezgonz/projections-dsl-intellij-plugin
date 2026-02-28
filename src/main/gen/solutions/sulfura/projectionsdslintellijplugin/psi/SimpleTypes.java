// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import solutions.sulfura.projectionsdslintellijplugin.psi.impl.*;

public interface SimpleTypes {

  IElementType PROJECTION = new ProjectionsDslElementType("PROJECTION");
  IElementType PROJECTION_CONTENT = new ProjectionsDslElementType("PROJECTION_CONTENT");
  IElementType PROJECTION_CONTENT_CONTAINER = new ProjectionsDslElementType("PROJECTION_CONTENT_CONTAINER");
  IElementType PROPERTY_DECL = new ProjectionsDslElementType("PROPERTY_DECL");

  IElementType PROJECTION_CONTAINER_END_CHAR = new ProjectionsDslTokenType("PROJECTION_CONTAINER_END_CHAR");
  IElementType PROJECTION_CONTAINER_START_CHAR = new ProjectionsDslTokenType("PROJECTION_CONTAINER_START_CHAR");
  IElementType PROJECTION_TYPE_ALIAS = new ProjectionsDslTokenType("PROJECTION_TYPE_ALIAS");
  IElementType PROPERTY_ALIAS = new ProjectionsDslTokenType("PROPERTY_ALIAS");
  IElementType PROPERTY_NAME = new ProjectionsDslTokenType("PROPERTY_NAME");
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
      else if (type == PROJECTION_CONTENT_CONTAINER) {
        return new ProjectionsDslProjectionContentContainerImpl(node);
      }
      else if (type == PROPERTY_DECL) {
        return new ProjectionsDslPropertyDeclImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
