package ek.kotsu.validator;

import ek.kotsu.basic.struct.ValidResult;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/24.
 */
public interface Validator {

    boolean support(Annotation strategyAnnotation);

    ValidResult validate(String input, String parameterName, Annotation strategyAnnotation);

}
