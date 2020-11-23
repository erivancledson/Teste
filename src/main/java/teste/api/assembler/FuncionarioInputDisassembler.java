package teste.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.api.model.input.FuncionarioInput;
import teste.domain.model.Cargo;
import teste.domain.model.Funcionario;

@Component
public class FuncionarioInputDisassembler {
	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public Funcionario toDomainObject(FuncionarioInput funcionarioInput) {
	        return modelMapper.map(funcionarioInput, Funcionario.class);
	    }
	    
	    public void copyToDomainObject(FuncionarioInput funcionarioInput, Funcionario funcionario) {	 
	    	funcionario.setCargo(new Cargo());
	    	
	        modelMapper.map(funcionarioInput, funcionario);
	    }
}
