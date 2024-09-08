package solutions.sulfura.projectionsdslintellijplugin.manualtests;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.PARAMETER})
public @interface TestAnnotation {

    String value();
}
