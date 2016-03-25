package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VFloat;
import ek.kotsu.basic.struct.ValidResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/25.
 */
@Component
public class FloatValidator implements Validator {
    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VFloat.class);
    }

    @Override
    public ValidResult validate(String input, String parameterName, Annotation strategyAnnotation) {
        VFloat vFloat = (VFloat) strategyAnnotation;
        float min = vFloat.min();
        float max = vFloat.max();
        if (min > Float.parseFloat(input) || max < Float.parseFloat(input)) {
            return new ValidResult(false, parameterName + " is beyond " + min + " to " + max);
        }
        return new ValidResult(true);
    }
}
