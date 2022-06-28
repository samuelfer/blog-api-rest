package br.com.marhasoft.blogapirest.blogapirest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDTO {

    private List<PostDTO> posts;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPage;
    private boolean last;

}
