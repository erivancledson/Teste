package teste.api.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioModel {

private Integer id;
	
	private String name;
	
	private Integer age;
	
	private OffsetDateTime birthdate;
	
	private String document;
	
	private CargoModel cargo;
	
	private List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
	
}
