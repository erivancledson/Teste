package teste.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.api.model.DepartamentoModel;
import teste.domain.model.Departamento;

@Component
public class DepartamentoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DepartamentoModel toModel(Departamento departamento) {
        return modelMapper.map(departamento, DepartamentoModel.class);
    }
    
    public List<DepartamentoModel> toCollectionModel(List<Departamento> departamentos) {
        return departamentos.stream()
                .map(cidade -> toModel(cidade))
                .collect(Collectors.toList());
    }
}
