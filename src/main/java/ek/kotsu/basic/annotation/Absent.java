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
public @interface Absent {

    /**
     * @return validation strategy.
     */
    int value();

    /**
     * @return whether this parameter is nullable.
     */
    boolean nullable() default false;

}
