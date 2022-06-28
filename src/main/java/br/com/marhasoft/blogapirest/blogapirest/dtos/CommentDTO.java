package br.com.marhasoft.blogapirest.blogapirest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String name;
    private String email;
    private String text;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime datePublished = LocalDateTime.now();

    public CommentDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName(getName());
        commentDTO.setEmail(getEmail());
        commentDTO.setText(getText());
    }

    public CommentDTO(CommentDTO commentDTO) {
        commentDTO.setName(getName());
        commentDTO.setEmail(getEmail());
        commentDTO.setText(getText());
    }
}
