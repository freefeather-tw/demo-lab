package com.example.demo.service;

import com.example.demo.entity.Users;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<Users> search(Predicate predicate, Pageable pageable);

    Users getById(UUID id);

    Users addUser(Users users);

    Users updateUser(Users users);

    void deleteUser(UUID id);

    Page<Users> findByName(String name, Pageable pageable);
}
