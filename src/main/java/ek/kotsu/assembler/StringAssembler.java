package ek.kotsu.assembler;

import org.springframework.stereotype.Component;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class StringAssembler implements Assembler {

    @Override
    public boolean support(Class targetObjectType) {
        return targetObjectType.equals(String.class);
    }

    @Override
    public Object assemble(String input, Class targetObjectType) {
        return input;
    }

}
