package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VShort;
import ek.kotsu.basic.struct.ValidResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/25.
 */
@Component
public class ShortValidator implements Validator {

    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VShort.class);
    }

    @Override
    public ValidResult validate(String input, String parameterName, Annotation strategyAnnotation) {
        VShort vShort = (VShort) strategyAnnotation;
        short min = vShort.min();
        short max = vShort.max();
        if (min > Short.valueOf(input) || max < Short.valueOf(input)) {
            return new ValidResult(false, parameterName + " is beyond " + min + " to " + max);
        }
        return new ValidResult(true);
    }

}
