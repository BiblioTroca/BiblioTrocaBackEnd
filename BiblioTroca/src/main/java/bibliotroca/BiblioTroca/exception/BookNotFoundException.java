package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{
        private static final long serialVersionUID = 1L;

        public BookNotFoundException() {
            super(String.format("O livro não foi encontrado"), null, false, false);
        }
    }

    // PESSINA MANDAR LEMBRAR AMANHA //
/*{
	"timestamp": "2023-09-14T20:21:01.925+00:00",
	"status": 404,
	"error": "Not Found",
	"trace": "bibliotroca.BiblioTroca.exception.BookNotFoundException: O livro não foi encontrado\r\n",
	"message": "O livro não foi encontrado",
	"path": "/api/v1/bibliotroca/desejos/2222222222"
}

{
	"timestamp": "2023-09-14T21:11:10.786+00:00",
	"status": 409,
	"error": "Conflict",
	"trace": "bibliotroca.BiblioTroca.exception.BookAlreadyRegistered: O Livro já foi adicionado\r\n",
	"message": "O Livro já foi adicionado",
	"path": "/api/v1/bibliotroca/desejos"
}
*/
