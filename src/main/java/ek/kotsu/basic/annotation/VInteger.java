package ek.kotsu.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eric Kim on 16/2/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@CustomValid(Integer.class)
public @interface VInteger {

    int max() default Integer.MAX_VALUE;

    int min() default Integer.MIN_VALUE;

}
