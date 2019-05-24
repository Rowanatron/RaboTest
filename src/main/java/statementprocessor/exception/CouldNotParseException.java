package statementprocessor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CouldNotParseException extends RuntimeException {

    public CouldNotParseException(String message) {
        super(message);
    }
}
