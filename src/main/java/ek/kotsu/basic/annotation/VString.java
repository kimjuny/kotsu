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
@CustomValid(String.class)
public @interface VString {

    /**
     * @return Max byte length of this String.
     */
    int max() default Integer.MAX_VALUE;

    /**
     * @return Min byte length of this String.
     */
    int min() default 0;

    /**
     * @return Input string should among one of these Strings.
     */
    String[] among() default {};

    /**
     * @return Regular expression.
     */
    String regex() default "";

}
