package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PointAlreadyCreatedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PointAlreadyCreatedException() {
		super(String.format("Já existe um registro de pontos para o usuário."), null, false, false);
	}
}