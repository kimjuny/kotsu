package ek.kotsu.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eric Kim on 16/3/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface MayAbsent {

    /**
     * @return validation strategy.
     */
    String value() default "";

    /**
     * @return whether this parameter is nullable.
     */
    boolean nullable() default false;

}
