package solutions.sulfura.projectionsdslintellijplugin.manualtests;


import solutions.sulfura.gend.dtos.Dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.PARAMETER})
public @interface TestAnnotation {

    Class<? extends Dto> projectedClass();
    String value();
}
