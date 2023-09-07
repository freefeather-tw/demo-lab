package com.example.demo.service.impl;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Users> search(Predicate predicate, Pageable pageable) {
        return userRepository.findAll(predicate, pageable);
    }

    public Users getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users addUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users updateUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<Users> findByName(String name, Pageable pageable) {

        return userRepository.findByName(name, pageable);
    }
}
