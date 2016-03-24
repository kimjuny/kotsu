package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VInteger;
import ek.kotsu.basic.struct.ValidResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/23.
 */
@Component
public class IntegerValidator implements Validator {

    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VInteger.class);
    }

    @Override
    public ValidResult validate(String input, String parameterName, Annotation strategyAnnotation) {
        VInteger vInteger = (VInteger) strategyAnnotation;
        int min = vInteger.min();
        int max = vInteger.max();
        if (min > Integer.parseInt(input) || max < Integer.parseInt(input)) {
            return new ValidResult(false, parameterName + " is not between " + min + " to " + max);
        }
        return new ValidResult(true);
    }
}
