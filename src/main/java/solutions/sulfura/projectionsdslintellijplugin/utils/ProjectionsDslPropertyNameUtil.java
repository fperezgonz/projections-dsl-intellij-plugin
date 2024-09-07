package solutions.sulfura.projectionsdslintellijplugin.utils;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import solutions.sulfura.projectionsdslintellijplugin.ProjectionDslRoot;
import solutions.sulfura.projectionsdslintellijplugin.ProjectionsDslLanguageFileType;
import solutions.sulfura.projectionsdslintellijplugin.psi.ProjectionsDslPropertyName;

import java.util.*;

public class ProjectionsDslPropertyNameUtil {

    /**
     * Searches the entire project for ProjectionDsl language files with instances of the ProjectionDsl property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<ProjectionsDslPropertyName> findProperties(Project project, String key) {
        List<ProjectionsDslPropertyName> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(ProjectionsDslLanguageFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            ProjectionDslRoot simpleFile = (ProjectionDslRoot) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                ProjectionsDslPropertyName[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, ProjectionsDslPropertyName.class);
                if (properties != null) {
                    for (ProjectionsDslPropertyName property : properties) {
                        if (key.equals(property.getText())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<ProjectionsDslPropertyName> findProperties(Project project) {
        List<ProjectionsDslPropertyName> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(ProjectionsDslLanguageFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            ProjectionDslRoot simpleFile = (ProjectionDslRoot) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                ProjectionsDslPropertyName[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, ProjectionsDslPropertyName.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the ProjectionDsl key/value pair.
     */
    public static @NotNull String findDocumentationComment(ProjectionsDslPropertyName property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }

}
