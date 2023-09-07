package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{
        private static final long serialVersionUID = 1L;

        public BookNotFoundException(String nameBook) {
            super(String.format("O livro não foi encontrado", nameBook), null, false, false);
        }
    }
