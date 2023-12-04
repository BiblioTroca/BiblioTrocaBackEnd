package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmailNotFoundException(String email) {
		super(String.format("O Email %s não foi encontrado", email), null, false, false);
	}
}