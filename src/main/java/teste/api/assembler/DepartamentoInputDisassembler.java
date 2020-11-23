package teste.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.api.model.input.DepartamentoInput;
import teste.domain.model.Departamento;

@Component
public class DepartamentoInputDisassembler {
	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public Departamento toDomainObject(DepartamentoInput departamentoInput) {
	        return modelMapper.map(departamentoInput, Departamento.class);
	    }
	    
	    public void copyToDomainObject(DepartamentoInput departamentoInput, Departamento departamento) {	        
	        modelMapper.map(departamentoInput, departamento);
	    }
}
