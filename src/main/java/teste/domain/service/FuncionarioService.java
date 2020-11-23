package teste.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teste.domain.exception.FuncionarioEmUsoException;
import teste.domain.exception.FuncionarioNaoEncontradoException;
import teste.domain.model.Cargo;
import teste.domain.model.Departamento;
import teste.domain.model.Funcionario;
import teste.domain.repository.FuncionarioRepository;


@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private CargoService cargoService;

	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		Cargo cargo = cargoService.buscarOuFalhar(funcionario.getCargo().getId());
		
		funcionario.setCargo(cargo);
		
		return funcionarioRepository.save(funcionario);
	}

	@Transactional
	public void excluir(Integer funcionarioId) {
		try {
			Funcionario funcionario = buscarOuFalhar(funcionarioId);

			funcionarioRepository.delete(funcionario);
			funcionarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new FuncionarioEmUsoException(funcionarioId);
		}
	}

	public Funcionario buscarOuFalhar(Integer funcionarioId) {
		return funcionarioRepository.findById(funcionarioId)
				.orElseThrow(
						() -> new FuncionarioNaoEncontradoException(
								funcionarioId));
	}
	
	public List<Funcionario> buscarPorDepartamento(Integer departamentoId) {
		return funcionarioRepository.findByDepartamento(departamentoId);
	}
	
	@Transactional
	public Funcionario adicionarDepartamento(Integer funcionarioId, Integer departamentoId){
		Funcionario funcionario = buscarOuFalhar(funcionarioId);
		Departamento departamento = departamentoService.buscarOuFalhar(departamentoId);
		
		funcionario.getDepartamentos().add(departamento);
		
		return salvar(funcionario);
	}
	
	@Transactional
	public Funcionario removerDepartamento(Integer funcionarioId, Integer departamentoId){
		Funcionario funcionario = buscarOuFalhar(funcionarioId);
		Departamento departamento = departamentoService.buscarOuFalhar(departamentoId);
		
		funcionario.getDepartamentos().remove(departamento);
		
		return salvar(funcionario);
	}
}
