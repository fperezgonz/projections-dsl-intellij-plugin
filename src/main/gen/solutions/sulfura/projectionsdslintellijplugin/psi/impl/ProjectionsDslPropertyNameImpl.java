// This is a generated file. Not intended for manual editing.
package solutions.sulfura.projectionsdslintellijplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslPropertyName;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslPsiUtil;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslVisitor;

public class ProjectionsDslPropertyNameImpl extends ASTWrapperPsiElement implements ProjectionsDslPropertyName {

  public ProjectionsDslPropertyNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ProjectionsDslVisitor visitor) {
    visitor.visitPropertyName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ProjectionsDslVisitor) accept((ProjectionsDslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public PsiReference @NotNull [] getReferences() {
    return ProjectionsDslPsiUtil.getReferences(this);
  }

}
