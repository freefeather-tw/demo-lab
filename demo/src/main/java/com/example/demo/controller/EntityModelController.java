package com.example.demo.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public abstract class EntityModelController<T> {

    final EntityModel<T> toEntityModel(T t) {
        return EntityModel.of(t);
    }

    final EntityModel<T> toEntityModel(T t, Link... links) {
        return EntityModel.of(t, links);
    }

    final Link selfLink() {
        return WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
    }

    abstract Link idLink(T t);
}
