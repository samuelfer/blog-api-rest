package br.com.marhasoft.blogapirest.blogapirest.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterDTO {

    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;

    @NotEmpty(message = "Login não pode ser vazio")
    private String username;

    @NotEmpty(message = "Email não pode ser vazio")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazio")
    private String password;
}
