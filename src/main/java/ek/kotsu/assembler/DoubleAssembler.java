package ek.kotsu.assembler;

import org.springframework.stereotype.Component;

/**
 * Created by Eric Kim on 16/3/25.
 */
@Component
public class DoubleAssembler implements Assembler {

    @Override
    public boolean support(Class targetObjectType) {
        return targetObjectType.equals(Double.class);
    }

    @Override
    public Object assemble(String input, Class targetObjectType) {
        return Double.valueOf(input);
    }

}
