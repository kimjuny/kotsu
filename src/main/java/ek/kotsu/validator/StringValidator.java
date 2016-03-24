package ek.kotsu.validator;

import ek.kotsu.basic.annotation.VString;
import ek.kotsu.basic.struct.ValidResult;
import ek.kotsu.common.InputStringChecker;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class StringValidator implements Validator {

    @Override
    public boolean support(Annotation strategyAnnotation) {
        return strategyAnnotation.annotationType().equals(VString.class);
    }

    @Override
    public ValidResult validate(String input, java.lang.String parameterName, Annotation strategyAnnotation) {
        VString vString = (VString) strategyAnnotation;
        int max = vString.max();
        int min = vString.min();
        String[] among = vString.among();
        String regex = vString.regex();
        if (!InputStringChecker.inByteSizeString(input, min, max)) {
            return new ValidResult(false, parameterName + " byte length is not between " + min + " to " + max);
        }
        if (!InputStringChecker.among(input, among)) {
            return new ValidResult(false, parameterName + " is not among " + Arrays.toString(among));
        }
        if (!"".equals(regex)) {
            if (!InputStringChecker.regex(input, regex)) {
                return new ValidResult(false, parameterName + " does not match regex '" + regex + "'");
            }
        }
        return new ValidResult(true);
    }

}
