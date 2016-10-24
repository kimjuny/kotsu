package ek.kotsu.assembler;

import ek.kotsu.basic.exception.SupportAssemblerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class AssemblerManager {

    @Autowired
    private List<Assembler> assemblers;

    public Optional<Object> assemble(Optional<String> extractedResult, Class targetObjectType) throws ParseException {
        if (null == extractedResult || !extractedResult.isPresent()) {
            return null;
        }
        Assembler assembler = this.getSupportAssembler(targetObjectType);
        if (null == assembler) {
            throw new SupportAssemblerNotFoundException("No assembler found for " + targetObjectType.getName());
        }
        return Optional.of(assembler.assemble(extractedResult.get(), targetObjectType));
    }

    private Assembler getSupportAssembler(Class clazz) {
        for (Assembler assembler : assemblers) {
            if (assembler.support(clazz)) {
                return assembler;
            }
        }
        return null;
    }

}
