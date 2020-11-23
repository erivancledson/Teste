package teste.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoInput {
	@Size(max=50)
	@NotBlank
	private String name;
}
