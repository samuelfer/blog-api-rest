package br.com.marhasoft.blogapirest.blogapirest.models;

import javax.persistence.*;

@Entity
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 15)
    private String name;
}
