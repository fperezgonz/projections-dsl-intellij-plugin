// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import solutions.sulfura.projectionsdslintellijplugin.psi.*;

public class ProjectionsDslProjectionImpl extends ASTWrapperPsiElement implements ProjectionsDslProjection {

  public ProjectionsDslProjectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ProjectionsDslVisitor visitor) {
    visitor.visitProjection(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ProjectionsDslVisitor) accept((ProjectionsDslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ProjectionsDslProjectionContent getProjectionContent() {
    return findNotNullChildByClass(ProjectionsDslProjectionContent.class);
  }

}
