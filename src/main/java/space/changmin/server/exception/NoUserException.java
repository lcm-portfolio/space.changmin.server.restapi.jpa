package space.changmin.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoUserException extends RuntimeException {
    public NoUserException(){
        super("가입되지 않은 유저입니다!");
    }
}
