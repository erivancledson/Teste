package teste.domain.exception;

public class DepartamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public DepartamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DepartamentoNaoEncontradoException(Integer estadoId) {
		this(String.format("Não existe um cadastro de departamento com código %d", estadoId));
	}

}
