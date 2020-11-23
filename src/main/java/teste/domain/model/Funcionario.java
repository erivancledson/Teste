package teste.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	private Integer age;
	
	private OffsetDateTime birthdate;
	
	@Column(nullable = false, length = 50)
	private String document;
	
	@ManyToOne
	private Cargo cargo;
	
	@ManyToMany
	@JoinTable(name = "funcionario_departamento", joinColumns = @JoinColumn(name="funcionario_id"), inverseJoinColumns = @JoinColumn(name="departamento_id"))
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	
}
