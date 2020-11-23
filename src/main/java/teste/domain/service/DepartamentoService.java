package teste.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teste.domain.exception.DepartamentoEmUsoException;
import teste.domain.exception.DepartamentoNaoEncontradoException;
import teste.domain.model.Departamento;
import teste.domain.repository.DepartamentoRepository;


@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Transactional
	public Departamento salvar(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}

	@Transactional
	public void excluir(Integer departamentoId) {
		try {
			Departamento departamento = buscarOuFalhar(departamentoId);

			departamentoRepository.delete(departamento);
			departamentoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DepartamentoEmUsoException(departamentoId);
		}
	}

	public Departamento buscarOuFalhar(Integer departamentoId) {
		return departamentoRepository.findById(departamentoId)
				.orElseThrow(
						() -> new DepartamentoNaoEncontradoException(
								departamentoId));
	}
}
