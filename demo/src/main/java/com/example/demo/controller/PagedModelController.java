package com.example.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

public abstract class PagedModelController<T> extends EntityModelController<T> {

    final PagedModel<EntityModel<T>> toPagedModel(Page<T> eneityPage, Link... links) {
        Page<EntityModel<T>> entityModelsPage = eneityPage.map(t -> toEntityModel(t, idLink(t)));

        return PagedModel.of(
                entityModelsPage.getContent(),
                new PagedModel.PageMetadata(eneityPage.getSize(),
                                            eneityPage.getNumber(),
                                            eneityPage.getTotalElements()),
                                            links);
    }

}
