package br.com.marhasoft.blogapirest.blogapirest.dtos;

import br.com.marhasoft.blogapirest.blogapirest.models.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotEmpty
    @Size(min = 5, message = "Título precisa ter no mínimo 5 caracteres")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    private String description;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime datePublished = LocalDateTime.now();
    private Set<Comment> comments;
}
