   package solutions.sulfura.projectionsdslintellijplugin.references;

   import com.intellij.openapi.util.TextRange;
   import com.intellij.psi.AbstractElementManipulator;
   import com.intellij.util.IncorrectOperationException;
   import org.jetbrains.annotations.Nullable;
   import org.jetbrains.annotations.NotNull;
   import solutions.sulfura.projectionsdslintellijplugin.psi.impl.ProjectionsDslPropertyDeclImpl;

   public class ProjectionsDslPropertyNameManipulator extends AbstractElementManipulator<ProjectionsDslPropertyDeclImpl> {

       @Override
       public @Nullable ProjectionsDslPropertyDeclImpl handleContentChange(@NotNull ProjectionsDslPropertyDeclImpl element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {

           //TODO
           // Implement how the content change should be handled
           // e.g. updating the name or other properties of the PSI element
           return null;

       }
   }