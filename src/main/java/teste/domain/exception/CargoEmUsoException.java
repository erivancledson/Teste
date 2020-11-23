package teste.domain.exception;

public class CargoEmUsoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CargoEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public CargoEmUsoException(Integer cargoId) {
		this(String.format("Cargo de código %d não pode ser removido, pois está em uso", cargoId));
	}

}
