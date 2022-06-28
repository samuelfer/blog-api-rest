package br.com.marhasoft.blogapirest.blogapirest.services;

import br.com.marhasoft.blogapirest.blogapirest.dtos.PostDTO;
import br.com.marhasoft.blogapirest.blogapirest.exceptions.ResourceNotFoundException;
import br.com.marhasoft.blogapirest.blogapirest.models.Post;
import br.com.marhasoft.blogapirest.blogapirest.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> listAll() {
        return postRepository.findAll();
    }

    public PostDTO findById(Long id) {
        Post post = findByIdOrErro(id);

        ModelMapper modelMapper = new ModelMapper();
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    public PostDTO save(PostDTO postDTO) {
        ModelMapper modelMapper = new ModelMapper();
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
        ModelMapper modelMapper = new ModelMapper();
        PostDTO postSaved = modelMapper.map(postUpdated, PostDTO.class);
        return postSaved;
    }

    public void delete(Long id) {
        Post post = findByIdOrErro(id);
        postRepository.delete(post);
    }

    private Post findByIdOrErro(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
