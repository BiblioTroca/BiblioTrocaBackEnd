package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InsuficientPointsException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InsuficientPointsException() {
		super(String.format("O usuário não possui pontos suficietes"), null, false, false);
	}
}
