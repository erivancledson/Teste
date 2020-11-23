package teste.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teste.domain.exception.CargoEmUsoException;
import teste.domain.exception.CargoNaoEncontradoException;
import teste.domain.model.Cargo;
import teste.domain.repository.CargoRepository;


@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Transactional
	public Cargo salvar(Cargo cargo) {
		return cargoRepository.save(cargo);
	}

	@Transactional
	public void excluir(Integer cargoId) {
		try {
			Cargo cargo = buscarOuFalhar(cargoId);

			cargoRepository.delete(cargo);
			cargoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new CargoEmUsoException(cargoId);
		}
	}

	public Cargo buscarOuFalhar(Integer cargoId) {
		return cargoRepository.findById(cargoId)
				.orElseThrow(
						() -> new CargoNaoEncontradoException(
								cargoId));
	}
}
