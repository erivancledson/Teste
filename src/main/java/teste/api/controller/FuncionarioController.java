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
import teste.api.assembler.FuncionarioInputDisassembler;
import teste.api.assembler.FuncionarioModelAssembler;
import teste.api.model.FuncionarioModel;
import teste.api.model.input.FuncionarioInput;
import teste.domain.model.Funcionario;
import teste.domain.repository.FuncionarioRepository;
import teste.domain.service.FuncionarioService;

@CrossOrigin(origins = "*")
@Api(value="API REST FUNCIONARIOS")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private FuncionarioModelAssembler funcionarioModelAssembler;
	
	@Autowired
	private FuncionarioInputDisassembler funcionarioInputDisassembler;

	@ApiOperation(value="Lista todos funcionarios")
	@GetMapping
	public List<FuncionarioModel> listar() {
		return funcionarioModelAssembler
				.toCollectionModel(repository.findAll());
	}

	@ApiOperation(value="Busca um unico funcionario")
	@GetMapping("/{funcionarioId}")
	public FuncionarioModel buscar(@PathVariable Integer funcionarioId) {
		Funcionario funcionario =
				service.buscarOuFalhar(funcionarioId);

		return funcionarioModelAssembler.toModel(funcionario);
	}

	@ApiOperation(value="Cadastra um funcionario")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FuncionarioModel
			adicionar(@RequestBody @Valid FuncionarioInput funcionarioInput) {
			Funcionario funcionario = funcionarioInputDisassembler
					.toDomainObject(funcionarioInput);

			return funcionarioModelAssembler
					.toModel(service.salvar(funcionario));
	}

	@ApiOperation(value="Atualiza um funcionario")
	@PutMapping("/{funcionarioId}")
	public FuncionarioModel atualizar(@PathVariable Integer funcionarioId,
			@RequestBody @Valid FuncionarioInput funcionarioInput) {
			Funcionario funcionarioAtual =
					service.buscarOuFalhar(funcionarioId);

			funcionarioInputDisassembler.copyToDomainObject(funcionarioInput,
					funcionarioAtual);

			return funcionarioModelAssembler
					.toModel(service.salvar(funcionarioAtual));
	}
	
	@ApiOperation(value="Deleta um funcionario")
	@DeleteMapping("/{funcionarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer funcionarioId) {
		service.excluir(funcionarioId);
	}
	
	@ApiOperation(value="Lista os funcionarios por departamento")
	@GetMapping("/departamento/{departamentoId}")
	public List<FuncionarioModel> buscarPorDepartamento(@PathVariable Integer departamentoId) {
		return funcionarioModelAssembler.toCollectionModel(service.buscarPorDepartamento(departamentoId));
	}
	
	@ApiOperation(value="Adiciona um departamento de um funcionario especifico")
	@PostMapping("/{funcionarioId}/departamento/{departamentoId}")
	public FuncionarioModel adicionarDepartamento(@PathVariable Integer funcionarioId,
			@PathVariable Integer departamentoId) {

			return funcionarioModelAssembler
					.toModel(service.adicionarDepartamento(funcionarioId, departamentoId));
	}
	@ApiOperation(value="Remove um departamento de um funcionario especifico")
	@DeleteMapping("/{funcionarioId}/departamento/{departamentoId}")
	public FuncionarioModel removerDepartamento(@PathVariable Integer funcionarioId,
			@PathVariable Integer departamentoId) {

			return funcionarioModelAssembler
					.toModel(service.removerDepartamento(funcionarioId, departamentoId));
	}

}
