package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDataException  extends  Exception{
        private static final long serialVersionUID = 1L;
        public InvalidDataException() {
            super(String.format("Dados inválidos"), null, false, false);
        }
    }

