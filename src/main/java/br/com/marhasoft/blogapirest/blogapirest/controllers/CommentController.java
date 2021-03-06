package br.com.marhasoft.blogapirest.blogapirest.controllers;

import br.com.marhasoft.blogapirest.blogapirest.dtos.CommentDTO;
import br.com.marhasoft.blogapirest.blogapirest.models.Comment;
import br.com.marhasoft.blogapirest.blogapirest.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/comments/{id}/post/{postId}")
    public Comment findCommentsByIdAndPost(@PathVariable("id") Long id, @PathVariable("postId") Long postId) {
        return commentService.findCommentByIdAndPostId(id, postId);
    }

    @GetMapping("/comments/post/{postId}")
    public List<Comment> findCommentsByPost(@PathVariable("postId") Long postId) {
        return commentService.findCommentsByPost(postId);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> save(@PathVariable("postId") Long id, @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.save(id, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/comments/{commentId}/post/{postId}")
    public ResponseEntity<Comment> update(@PathVariable("commentId") Long commentId,
                                          @PathVariable("postId") Long postId,
                                          @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.update(commentId, postId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        commentService.delete(id);
        return new ResponseEntity<>("Comment deleted with success", HttpStatus.NO_CONTENT);
    }
}
