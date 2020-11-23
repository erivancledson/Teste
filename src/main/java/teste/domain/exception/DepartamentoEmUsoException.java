package teste.domain.exception;

public class DepartamentoEmUsoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public DepartamentoEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public DepartamentoEmUsoException(Integer cargoId) {
		this(String.format("Departamento de código %d não pode ser removido, pois está em uso", cargoId));
	}

}
