package ek.kotsu.assembler;

import org.springframework.stereotype.Component;

/**
 * Created by Eric Kim on 16/3/25.
 */
@Component
public class FloatAssembler implements Assembler {

    @Override
    public boolean support(Class targetObjectType) {
        return targetObjectType.equals(Float.class);
    }

    @Override
    public Object assemble(String input, Class targetObjectType) {
        return Float.valueOf(input);
    }

}
