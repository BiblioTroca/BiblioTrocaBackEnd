package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistryNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RegistryNotFoundException(Long registry) {
		super(String.format("O Cadastro %s não foi encontrado em nenhum livro", registry), null, false, false);
	}
}
