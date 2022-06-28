package br.com.marhasoft.blogapirest.blogapirest.services;

import br.com.marhasoft.blogapirest.blogapirest.dtos.CommentDTO;
import br.com.marhasoft.blogapirest.blogapirest.exceptions.BlogAppException;
import br.com.marhasoft.blogapirest.blogapirest.exceptions.ResourceNotFoundException;
import br.com.marhasoft.blogapirest.blogapirest.models.Comment;
import br.com.marhasoft.blogapirest.blogapirest.models.Post;
import br.com.marhasoft.blogapirest.blogapirest.repositories.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostService postService;

    private Comment findByIdOrErro(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    public Comment findCommentByIdAndPostId(Long postId, Long id) {
        Post post = postService.findByIdOrErro(postId);
        Comment comment = findByIdOrErro(id);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the post");
        }
        return comment;
    }

    public List<Comment> findCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public CommentDTO save(Long postId, CommentDTO commentDTO) {
        Post post = postService.findByIdOrErro(postId);

        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        comment.setPost(post);
        Comment commentSaved = commentRepository.save(comment);

        CommentDTO commentDTOSaved = modelMapper.map(commentSaved, CommentDTO.class);
        return commentDTOSaved;
    }
}
