package tr.com.tkeskin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotfoundException extends RuntimeException {

    public EntityNotfoundException(String param) {

        super(param);
    }
}