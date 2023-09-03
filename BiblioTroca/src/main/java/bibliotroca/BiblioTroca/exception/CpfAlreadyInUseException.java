package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfAlreadyInUseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CpfAlreadyInUseException(String cpf) {
		super(String.format("O CPF %s já foi cadastrado", cpf));
	}
}