package br.com.marhasoft.blogapirest.blogapirest.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BlogAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BlogAppException(HttpStatus status, String message, String messageN) {
        super();
        this.status = status;
        this.message = message;
        this.message = messageN;
    }
}
