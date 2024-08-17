package space.changmin.server.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        this("Invalid Token");
    }


    public InvalidTokenException(String message) {
        super(message);
    }
}
