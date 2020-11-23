package teste.domain.exception;

public class FuncionarioEmUsoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FuncionarioEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public FuncionarioEmUsoException(Integer cargoId) {
		this(String.format("Funcionário de código %d não pode ser removido, pois está em uso", cargoId));
	}

}
