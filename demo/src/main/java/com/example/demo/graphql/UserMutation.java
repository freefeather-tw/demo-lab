package com.example.demo.graphql;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public Users createUser(String name, Integer age) {
        Users users = Users.builder()
                .name(name)
                .age(age)
                .id(UUID.randomUUID())
                .build();

        userRepository.save(users);
        log.debug("createUser: user={}", users);

        return users;
    }

    public Users updateUser(UUID id, String name, Integer age) {
        Users users = Users.builder()
                .name(name)
                .age(age)
                .id(id)
                .build();

        userRepository.save(users);
        log.debug("updateUser: user={}", users);

        return users;
    }

    public boolean deleteUser(UUID id) {
        try {
            userRepository.deleteById(id);
            log.debug("deleteUser: id={}", id);
            return true;
        } catch (Exception e) {
            log.error("deleteUser: id={}", id, e);
            return false;
        }
    }

}
