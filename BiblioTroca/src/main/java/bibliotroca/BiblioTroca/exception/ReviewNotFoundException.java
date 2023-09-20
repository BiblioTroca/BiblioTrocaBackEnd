package bibliotroca.BiblioTroca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends  Exception{
    private static final long serialVersionUID = 1L;

    public ReviewNotFoundException() {
        super(String.format("A avaliação não foi encontrada"), null, false, false);
    }
}
