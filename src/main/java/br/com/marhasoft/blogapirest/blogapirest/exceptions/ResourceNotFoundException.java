package br.com.marhasoft.blogapirest.blogapirest.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private  static final long serialVersionUID = 1L;

    private String nameResource;
    private String nameField;
    private long valorField;

    public ResourceNotFoundException(String nameResource, String nameField, long valorField) {
        super(String .format("%s não encontrado com %s : '%s'", nameResource, nameField, valorField));
        this.nameResource = nameResource;
        this.nameField = nameField;
        this.valorField = valorField;
    }
}
