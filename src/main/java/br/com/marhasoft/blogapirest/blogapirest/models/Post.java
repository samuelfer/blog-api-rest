package br.com.marhasoft.blogapirest.blogapirest.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private LocalDateTime datePublish = LocalDateTime.now();
}
