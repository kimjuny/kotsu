package ek.kotsu.resolver;

import ek.kotsu.assembler.AssemblerManager;
import ek.kotsu.basic.annotation.Required;
import ek.kotsu.common.StrategyAnnotationManager;
import ek.kotsu.extractor.ExtractorManager;
import ek.kotsu.validator.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/3/23.
 */
public class RequiredParamResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ExtractorManager extractorManager;

    @Autowired
    private ValidatorManager validatorManager;

    @Autowired
    private AssemblerManager assemblerManager;

    @Autowired
    private StrategyAnnotationManager strategyAnnotationManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Required.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Required required = parameter.getParameterAnnotation(Required.class);
        String strategy = required.value();
        boolean nullable = required.nullable();
        Annotation strategyAnnotation = strategyAnnotationManager.pickStrategyAnnotation(parameter.getParameterAnnotations());
        if (null == strategyAnnotation) {
            strategyAnnotation = strategyAnnotationManager.getStrategyAnnotation(strategy);
        }
        // 1, extract
        Optional<String> extractedResult = extractorManager.extract(webRequest, parameter.getParameterName());
        // 2, validate
        validatorManager.validate(extractedResult, parameter.getParameterName(), strategyAnnotation, nullable, true);
        // 3, assemble
        Class targetObjectType = strategyAnnotationManager.getTargetObjectType(strategyAnnotation);
        return assemblerManager.assemble(extractedResult, targetObjectType).get();
    }

}
