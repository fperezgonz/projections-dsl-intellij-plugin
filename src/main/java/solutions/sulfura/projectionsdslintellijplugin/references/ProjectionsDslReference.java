package solutions.sulfura.projectionsdslintellijplugin.references;

import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil;

import java.util.Arrays;
import java.util.List;

import static solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil.getContextAnnotation;
import static solutions.sulfura.projectionsdslintellijplugin.utils.ProjectionsDslUtil.getPathToProperty;

public class ProjectionsDslReference extends PsiReferenceBase<PsiElement> {
    public ProjectionsDslReference(PsiElement element) {
        super(element);
    }

    @Nullable
    @Override
    public PsiElement resolve() {

        String fieldName = getElement().getText();

        PsiAnnotation psiAnnotation = getContextAnnotation(getElement());

        if (psiAnnotation == null) {
            return null;
        }

        //Build path of current element
        List<String> projectionPropertyPath = getPathToProperty(getElement());

        //Find The projected class for the path of the current element
        PsiClass psiNestedProjectionRootClass = ProjectionsDslUtil.findProjectedClassAtPath(psiAnnotation, projectionPropertyPath);

        if (psiNestedProjectionRootClass == null) {
            return null;
        }

        //Find the field that corresponds to the current element and return it
        PsiField[] fields = psiNestedProjectionRootClass.getAllFields();

        PsiField psiField = Arrays.stream(fields)
                .filter(field -> {
                    System.out.println(fieldName + " " + field.getName());
                    return field.getName().contains(fieldName);
                })
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