package ek.kotsu.assembler;

import org.springframework.stereotype.Component;

/**
 * Created by Eric Kim on 16/3/25.
 */
@Component
public class LongAssembler implements Assembler {

    @Override
    public boolean support(Class targetObjectType) {
        return targetObjectType.equals(Long.class);
    }

    @Override
    public Object assemble(String input, Class targetObjectType) {
        return Long.valueOf(input);
    }

}
