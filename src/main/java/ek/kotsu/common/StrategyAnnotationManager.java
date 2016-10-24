package ek.kotsu.common;

import ek.kotsu.basic.annotation.CustomValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class StrategyAnnotationManager {

    @Autowired
    private StrategyMapper strategyMapper;

    private Map<Integer, Annotation> mapper = new HashMap<>();

    private boolean isCustomValid(Annotation annotation) {
        Annotation[] annotationsOfAnnotation = annotation.annotationType().getAnnotations();
        for (Annotation annotationOfAnnotation : annotationsOfAnnotation) {
            if (annotationOfAnnotation.annotationType().equals(CustomValid.class)) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void init() throws IllegalAccessException {
        Class clazz = strategyMapper.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (isCustomValid(annotation)) {
                    field.setAccessible(true);
                    mapper.put((Integer) field.get(null), annotation);
                }
            }
        }
    }

    public Annotation getStrategyAnnotation(int strategy) {
        return mapper.get(strategy);
    }

    public Annotation pickStrategyAnnotation(Annotation... annotations) {
        for (Annotation annotation : annotations) {
            if (isStrategyAnnotation(annotation)) {
                return annotation;
            }
        }
        return null;
    }

    public boolean containsStrategyAnnotation(Annotation... annotations) {
        return null != pickStrategyAnnotation(annotations);
    }

    public boolean isStrategyAnnotation(Annotation annotation) {
        return null != annotation.annotationType().getAnnotation(CustomValid.class);
    }

    public Class getTargetObjectType(int strategy) {
        Annotation strategyAnnotation = getStrategyAnnotation(strategy);
        return getTargetObjectType(strategyAnnotation);
    }

    public Class getTargetObjectType(Annotation strategyAnnotation) {
        CustomValid customValid = strategyAnnotation.annotationType().getAnnotation(CustomValid.class);
        return customValid.value();
    }
}
