package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class BookAlreadyRegistered extends  Exception{
     private static final long serialVersionUID = 1L;

        public BookAlreadyRegistered() {
            super(String.format("O Livro já foi adicionado"), null, false, false);
        }
    }

