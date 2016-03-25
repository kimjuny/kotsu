package ek.kotsu.validator;

import ek.kotsu.basic.exception.StrategyNotFoundException;
import ek.kotsu.basic.exception.SupportValidationNotFoundException;
import ek.kotsu.basic.exception.SyntaxException;
import ek.kotsu.basic.struct.ValidResult;
import ek.kotsu.common.StrategyAnnotationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/2/25.
 */
@Component
public class ValidatorManager {

    @Autowired
    private List<Validator> validators;

    public void validate(Optional<String> input, String parameterName, Annotation strategyAnnotation, boolean nullable, boolean required) throws SyntaxException {
        if (null == input) {
            if (required) {
                throw new SyntaxException(parameterName + " is required.");
            }
            return;
        }
        if (!input.isPresent() || "null".equals(input.get())) {
            if (!nullable) {
                throw new SyntaxException("Null value cannot be assigned to " + parameterName + ".");
            }
            return;
        }
        Validator validator = getSupportValidator(strategyAnnotation);
        if (null == validator) {
            throw new SupportValidationNotFoundException("No validator found for strategy annotation " + strategyAnnotation.annotationType().getName() + ".");
        }
        ValidResult validResult = validator.validate(input.get(), parameterName, strategyAnnotation);
        if (!validResult.getResult()) {
            throw new SyntaxException(validResult.getMessage());
        }
    }

    public Validator getSupportValidator(Annotation strategyAnnotation) {
        for (Validator validator : validators) {
            if (validator.support(strategyAnnotation)) {
                return validator;
            }
        }
        return null;
    }

}
