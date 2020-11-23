package teste.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import teste.api.assembler.CargoInputDisassembler;
import teste.api.assembler.CargoModelAssembler;
import teste.api.model.CargoModel;
import teste.api.model.input.CargoInput;
import teste.domain.model.Cargo;
import teste.domain.repository.CargoRepository;
import teste.domain.service.CargoService;

@CrossOrigin(origins = "*")
@Api(value="API REST CARGO")
@RestController
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService service;

	@Autowired
	private CargoRepository repository;
	
	@Autowired
	private CargoModelAssembler cargoModelAssembler;
	
	@Autowired
	private CargoInputDisassembler cargoInputDisassembler;

	@ApiOperation(value="Lista todos cargos")
	@GetMapping
	public List<CargoModel> listar() {
		return cargoModelAssembler
				.toCollectionModel(repository.findAll());
	}
	
	@ApiOperation(value="Busca um unico cargo")
	@GetMapping("/{cargoId}")
	public CargoModel buscar(@PathVariable Integer cargoId) {
		Cargo cargo =
				service.buscarOuFalhar(cargoId);

		return cargoModelAssembler.toModel(cargo);
	}
	
	@ApiOperation(value="Cadastra um cargo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CargoModel
			adicionar(@RequestBody @Valid CargoInput cargoInput) {
			Cargo cargo = cargoInputDisassembler
					.toDomainObject(cargoInput);

			return cargoModelAssembler
					.toModel(service.salvar(cargo));
	}

	@ApiOperation(value="Atualiza um cargo")
	@PutMapping("/{cargoId}")
	public CargoModel atualizar(@PathVariable Integer cargoId,
			@RequestBody @Valid CargoInput cargoInput) {
			Cargo cargoAtual =
					service.buscarOuFalhar(cargoId);

			cargoInputDisassembler.copyToDomainObject(cargoInput,
					cargoAtual);

			return cargoModelAssembler
					.toModel(service.salvar(cargoAtual));
	}
	
	@ApiOperation(value="Deleta um cargo")
	@DeleteMapping("/{cargoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer cargoId) {
		service.excluir(cargoId);
	}

}
