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

public class ProjectionsDslProjectionContentImpl extends ASTWrapperPsiElement implements ProjectionsDslProjectionContent {

  public ProjectionsDslProjectionContentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ProjectionsDslVisitor visitor) {
    visitor.visitProjectionContent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ProjectionsDslVisitor) accept((ProjectionsDslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ProjectionsDslProjection> getProjectionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ProjectionsDslProjection.class);
  }

  @Override
  @NotNull
  public List<ProjectionsDslPropertyName> getPropertyNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ProjectionsDslPropertyName.class);
  }

}
