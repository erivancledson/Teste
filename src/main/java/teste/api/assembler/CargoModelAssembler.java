package teste.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.api.model.CargoModel;
import teste.domain.model.Cargo;

@Component
public class CargoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CargoModel toModel(Cargo cargo) {
        return modelMapper.map(cargo, CargoModel.class);
    }
    
    public List<CargoModel> toCollectionModel(List<Cargo> cargos) {
        return cargos.stream()
                .map(cidade -> toModel(cidade))
                .collect(Collectors.toList());
    }
}
