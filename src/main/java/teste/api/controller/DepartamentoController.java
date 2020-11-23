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
import teste.api.assembler.DepartamentoInputDisassembler;
import teste.api.assembler.DepartamentoModelAssembler;
import teste.api.model.DepartamentoModel;
import teste.api.model.input.DepartamentoInput;
import teste.domain.model.Departamento;
import teste.domain.repository.DepartamentoRepository;
import teste.domain.service.DepartamentoService;

@CrossOrigin(origins = "*")
@Api(value="API REST DEPARTAMENTO")
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@Autowired
	private DepartamentoRepository repository;
	
	@Autowired
	private DepartamentoModelAssembler departamentoModelAssembler;
	
	@Autowired
	private DepartamentoInputDisassembler departamentoInputDisassembler;

	@ApiOperation(value="Lista todos departamentos")
	@GetMapping
	public List<DepartamentoModel> listar() {
		return departamentoModelAssembler
				.toCollectionModel(repository.findAll());
	}

	@ApiOperation(value="Busca um unico departamento")
	@GetMapping("/{departamentoId}")
	public DepartamentoModel buscar(@PathVariable Integer departamentoId) {
		Departamento departamento =
				service.buscarOuFalhar(departamentoId);

		return departamentoModelAssembler.toModel(departamento);
	}

	@ApiOperation(value="Cadastra um departamento")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DepartamentoModel
			adicionar(@RequestBody @Valid DepartamentoInput departamentoInput) {
			Departamento departamento = departamentoInputDisassembler
					.toDomainObject(departamentoInput);

			return departamentoModelAssembler
					.toModel(service.salvar(departamento));
	}

	@ApiOperation(value="Atualiza um departamento")
	@PutMapping("/{departamentoId}")
	public DepartamentoModel atualizar(@PathVariable Integer departamentoId,
			@RequestBody @Valid DepartamentoInput departamentoInput) {
			Departamento departamentoAtual =
					service.buscarOuFalhar(departamentoId);

			departamentoInputDisassembler.copyToDomainObject(departamentoInput,
					departamentoAtual);

			return departamentoModelAssembler
					.toModel(service.salvar(departamentoAtual));
	}
	
	@ApiOperation(value="Deleta um departamento")
	@DeleteMapping("/{departamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer departamentoId) {
		service.excluir(departamentoId);
	}

}
