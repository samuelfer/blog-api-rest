package br.com.marhasoft.blogapirest.blogapirest.dtos;

import br.com.marhasoft.blogapirest.blogapirest.models.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime datePublished = LocalDateTime.now();
    private Set<Comment> comments;
}
