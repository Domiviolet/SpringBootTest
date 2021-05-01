package com.www.http.kits.exercise.security.draft.reflection.auxiliary;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/18 19:50:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Documented
public @interface StuCharacteristicAnnotation {

    String name() default "why";
    int age() default 18;

}
