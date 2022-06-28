package br.com.marhasoft.blogapirest.blogapirest.services;

import br.com.marhasoft.blogapirest.blogapirest.dtos.PostDTO;
import br.com.marhasoft.blogapirest.blogapirest.dtos.PostResponseDTO;
import br.com.marhasoft.blogapirest.blogapirest.exceptions.ResourceNotFoundException;
import br.com.marhasoft.blogapirest.blogapirest.models.Post;
import br.com.marhasoft.blogapirest.blogapirest.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;

    public Post findByIdOrErro(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    public PostResponseDTO findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> content = posts.getContent();

        List<PostDTO> postContent = content.stream().map(post -> mapPostToPostDTO(post))
                .collect(Collectors.toList());

        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setPosts(postContent);
        postResponseDTO.setPageNumber(posts.getNumber());
        postResponseDTO.setTotalElements(posts.getTotalElements());
        postResponseDTO.setTotalPage(posts.getTotalPages());
        postResponseDTO.setPageSize(posts.getSize());
        postResponseDTO.setLast(posts.isLast());
        return postResponseDTO;
    }

    public PostDTO findById(Long id) {
        Post post = findByIdOrErro(id);

        ModelMapper modelMapper = new ModelMapper();
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    public PostDTO save(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        Post newPost = postRepository.save(post);
        PostDTO postDTOSave = modelMapper.map(newPost, PostDTO.class);
        return postDTOSave;
    }

    public PostDTO update(PostDTO postDTO, Long id) {
        Post post = findByIdOrErro(id);

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());

        Post postUpdated = postRepository.save(post);

        PostDTO postSaved = modelMapper.map(postUpdated, PostDTO.class);
        return postSaved;
    }

    public void delete(Long id) {
        Post post = findByIdOrErro(id);
        postRepository.delete(post);
    }

    private PostDTO mapPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setTitle(postDTO.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setDatePublished(post.getDatePublish());
        return postDTO;
    }
}
