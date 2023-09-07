package com.example.demo.graphql;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    public Iterable<Users> getUserList() {
        return userRepository.findAll();
    }

    public Users getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}
