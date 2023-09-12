package bibliotroca.BiblioTroca.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class ReviewAlreadyExists extends  Exception{
    private static final long serialVersionUID = 1L;

    public ReviewAlreadyExists() {
        super(String.format("A avaliação já foi adicionada"), null, false, false);
    }
}

