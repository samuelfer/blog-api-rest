package br.com.marhasoft.blogapirest.blogapirest.controllers;

import br.com.marhasoft.blogapirest.blogapirest.dtos.PostDTO;
import br.com.marhasoft.blogapirest.blogapirest.models.Post;
import br.com.marhasoft.blogapirest.blogapirest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<Post> listAll(@RequestParam(value = "pageNumber", defaultValue = "0", required = false)
                                                    int pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false)
                                                int pageSize) {
        return new ResponseEntity(postService.listAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.save(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.update(postDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted with success", HttpStatus.NO_CONTENT);
    }
}
