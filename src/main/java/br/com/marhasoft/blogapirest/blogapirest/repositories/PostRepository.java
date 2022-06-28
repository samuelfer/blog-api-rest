package br.com.marhasoft.blogapirest.blogapirest.repositories;

import br.com.marhasoft.blogapirest.blogapirest.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
