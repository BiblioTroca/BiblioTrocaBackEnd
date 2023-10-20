package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TransactionNotFoundException() {
		super(String.format("Transação não encontrada"), null, false, false);
	}
}
