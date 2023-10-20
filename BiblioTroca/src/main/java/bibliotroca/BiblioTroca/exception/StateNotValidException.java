package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StateNotValidException extends Exception {
	private static final long serialVersionUID = 1L;

    public StateNotValidException() {
        super(String.format("A condição do livro é inválida. Os valores aceitos são 'Novo', 'Seminovo' ou 'Usado'."), null, false, false);
    }
}
