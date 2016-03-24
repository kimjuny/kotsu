package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VDouble;
import ek.kotsu.basic.struct.ValidResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class DoubleValidator implements Validator {

    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VDouble.class);
    }

    @Override
    public ValidResult validate(String input, String parameterName, Annotation strategyAnnotation) {
        VDouble vDouble = (VDouble) strategyAnnotation;
        double min = vDouble.min();
        double max = vDouble.max();
        if (min > Double.parseDouble(input) || max < Double.parseDouble(input)) {
            return new ValidResult(false, parameterName + " is not between " + min + " to " + max);
        }
        return new ValidResult(true);
    }

}
