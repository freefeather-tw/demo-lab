package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends PagedModelController<Users> {

    @Autowired
    private UserService userService;

    @GetMapping
    public PagedModel<EntityModel<Users>> search(@QuerydslPredicate(root = Users.class) Predicate predicate,
                                                 @PageableDefault(size = 3) Pageable pageable) {
        //log.debug("search: predicate={}, pageable={}", predicate, pageable);
        return toPagedModel(userService.search(predicate, pageable), selfLink());
    }

    @GetMapping("/{id}")
    public EntityModel<Users> getById(@PathVariable("id") UUID id) {
        Users users = userService.getById(id);
        if (users == null) {
            throw new EntityNotFoundException("User not found");
        }
        return toEntityModel(users, idLink(users));
    }

    @PostMapping
    public EntityModel<Users> addUser(@RequestBody Users users) {
        users.setId(UUID.randomUUID());
        userService.addUser(users);
        log.debug("addUser: user={}", users);

        return toEntityModel(users, idLink(users));
    }

    @PutMapping
    public EntityModel<Users> updateUser(@RequestBody Users users) {
        userService.updateUser(users);
        log.debug("updateUser: user={}", users);

        return toEntityModel(users, idLink(users));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        log.debug("deleteUser: id={}", id);
    }

    @GetMapping("/findByName")
    public PagedModel<EntityModel<Users>> findByName(@Nullable @RequestParam("name") String name,
                                                     @PageableDefault(size = 1) Pageable pageable) {

        return toPagedModel(userService.findByName(name, pageable), selfLink());
    }

    Link idLink(Users users) {
        if (null == users) {
            return selfLink();
        }

        return WebMvcLinkBuilder.linkTo(UserController.class).slash(users.getId()).withSelfRel();
    }
}
