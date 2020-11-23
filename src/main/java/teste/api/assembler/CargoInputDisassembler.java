package teste.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.api.model.input.CargoInput;
import teste.domain.model.Cargo;

@Component
public class CargoInputDisassembler {
	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public Cargo toDomainObject(CargoInput cargoInput) {
	        return modelMapper.map(cargoInput, Cargo.class);
	    }
	    
	    public void copyToDomainObject(CargoInput cargoInput, Cargo cargo) {	        
	        modelMapper.map(cargoInput, cargo);
	    }
}
