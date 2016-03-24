package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VLong;
import ek.kotsu.basic.struct.ValidResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class LongValidator implements Validator {

    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VLong.class);
    }

    @Override
    public ValidResult validate(String input, String parameterName, Annotation strategyAnnotation) {
        VLong vLong = (VLong) strategyAnnotation;
        long min = vLong.min();
        long max = vLong.max();
        if (min > Long.parseLong(input) || max < Long.parseLong(input)) {
            return new ValidResult(false, parameterName + " is not between " + min + " to " + max);
        }
        return new ValidResult(true);
    }

}
