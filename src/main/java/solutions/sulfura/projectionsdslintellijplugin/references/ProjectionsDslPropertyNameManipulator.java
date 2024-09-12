   package solutions.sulfura.projectionsdslintellijplugin.references;

   import com.intellij.openapi.util.TextRange;
   import com.intellij.psi.AbstractElementManipulator;
   import com.intellij.util.IncorrectOperationException;
   import org.jetbrains.annotations.Nullable;
   import solutions.sulfura.projectionsdslintellijplugin.psi.impl.ProjectionsDslPropertyNameImpl;
   import org.jetbrains.annotations.NotNull;

   public class ProjectionsDslPropertyNameManipulator extends AbstractElementManipulator<ProjectionsDslPropertyNameImpl> {

       @Override
       public @Nullable ProjectionsDslPropertyNameImpl handleContentChange(@NotNull ProjectionsDslPropertyNameImpl element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {

           //TODO
           // Implement how the content change should be handled
           // e.g. updating the name or other properties of the PSI element
           return null;

       }
   }