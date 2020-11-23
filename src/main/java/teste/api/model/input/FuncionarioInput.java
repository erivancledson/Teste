package teste.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioInput {
	
	@Size(max=50)
	@NotBlank
	private String name;

	@NotNull
	private Integer age;

	@NotNull
	private OffsetDateTime birthdate;

	@Size(max=50)
	@NotBlank
	private String document;

	@NotNull
	private CargoIdInput cargo;
}
