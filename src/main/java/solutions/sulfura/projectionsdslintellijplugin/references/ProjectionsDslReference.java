package solutions.sulfura.projectionsdslintellijplugin.references;

import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes;
import solutions.sulfura.projectionsdslintellijplugin.psi.impl.ProjectionsDslPropertyDeclImpl;
import solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static solutions.sulfura.projectionsdslintellijplugin.psi.SimpleTypes.PROPERTY_NAME;
import static solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil.getContextAnnotation;
import static solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil.getPathToProperty;

public class ProjectionsDslReference extends PsiReferenceBase<PsiElement> {
    public ProjectionsDslReference(PsiElement element) {
        super(element);
    }

    @Nullable
    @Override
    public PsiElement resolve() {


        PsiAnnotation psiAnnotation = getContextAnnotation(getElement());

        if (psiAnnotation == null) {
            return null;
        }

        //Build path of the current element
        List<String> projectionPropertyPath = getPathToProperty(getElement());

        //Find The projected class for the path of the current element
        PsiClass psiNestedProjectionRootClass = ProjectionsDslUtil.findProjectedClassAtPath(psiAnnotation, projectionPropertyPath);

        if (psiNestedProjectionRootClass == null) {
            return null;
        }

        //Find the field that corresponds to the current element and return it
        PsiField[] fields = psiNestedProjectionRootClass.getAllFields();
        Optional<ASTNode> propertyNameNode = Arrays.stream(this.getElement().getNode().getChildren(TokenSet.ANY))
                .filter(node -> node.getElementType() == PROPERTY_NAME)
                .findFirst();
        String propertyName = propertyNameNode.get().getText();
        PsiField psiField = Arrays.stream(fields)
                .filter(field -> field.getName().contains(propertyName))
                .findFirst().orElse(null);

        return psiField;

    }

    @NotNull
    @Override
    public Object[] getVariants() {
        // You can implement this method to show auto-complete suggestions
        return new Object[0];
    }
}