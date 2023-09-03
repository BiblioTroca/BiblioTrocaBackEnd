package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CpfNotFoundException(String cpf) {
		super(String.format("O CPF %s não foi encontrado", cpf));
	}
}