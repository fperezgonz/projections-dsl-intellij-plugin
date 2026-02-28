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
import com.intellij.psi.PsiReference;

public class ProjectionsDslPropertyDeclImpl extends ASTWrapperPsiElement implements ProjectionsDslPropertyDecl {

  public ProjectionsDslPropertyDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ProjectionsDslVisitor visitor) {
    visitor.visitPropertyDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ProjectionsDslVisitor) accept((ProjectionsDslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ProjectionsDslProjection getProjection() {
    return findChildByClass(ProjectionsDslProjection.class);
  }

  @Override
  public @NotNull PsiReference[] getReferences() {
    return ProjectionsDslPsiUtil.getReferences(this);
  }

}
