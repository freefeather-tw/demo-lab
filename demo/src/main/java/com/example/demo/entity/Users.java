package com.example.demo.entity;

import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "userList")
public class Users {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

}
