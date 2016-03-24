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
@CustomValid(Double.class)
public @interface VDouble {

    double min() default Double.MIN_VALUE;

    double max() default Double.MAX_VALUE;

}
