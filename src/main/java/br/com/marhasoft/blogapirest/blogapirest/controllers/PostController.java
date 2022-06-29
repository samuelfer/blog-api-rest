package br.com.marhasoft.blogapirest.blogapirest.controllers;

import br.com.marhasoft.blogapirest.blogapirest.dtos.PostDTO;
import br.com.marhasoft.blogapirest.blogapirest.dtos.PostResponseDTO;
import br.com.marhasoft.blogapirest.blogapirest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<PostResponseDTO> findAll(@RequestParam(value = "pageNumber",
            defaultValue = PAGE_NUMBER, required = false) int pageNumber,
                                                   @RequestParam(value = "pageSize",
            defaultValue = PAGE_SIZE, required = false) int pageSize) {
        return new ResponseEntity(postService.findAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> save(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.save(postDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable("id") Long id, @Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.update(postDTO, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted with success", HttpStatus.NO_CONTENT);
    }
}
