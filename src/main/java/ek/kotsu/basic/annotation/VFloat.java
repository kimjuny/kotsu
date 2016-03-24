package ek.kotsu.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@CustomValid(Float.class)
public @interface VFloat {

    float min() default Float.MIN_VALUE;

    float max() default Float.MAX_VALUE;

}
