package ek.kotsu.assembler;

/**
 * Created by Eric Kim on 16/2/27.
 */
public interface Assembler {

    boolean support(Class targetObjectType);

    Object assemble(String input, Class targetObjectType);

}
