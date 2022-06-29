package br.com.marhasoft.blogapirest.blogapirest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;

    @NotEmpty(message = "Email não pode ser vazio")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comentário precisa ter no mínimo 10 caracteres")
    private String text;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime datePublished = LocalDateTime.now();
}
