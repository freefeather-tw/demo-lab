package com.example.demo.repository;

import com.example.demo.entity.QUsers;
import com.example.demo.entity.Users;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//@RepositoryRestResource(collectionResourceRel = "User", path = "User")
public interface UserRepository extends PagingAndSortingRepository<Users, UUID>,
        QuerydslPredicateExecutor<Users>,
        QuerydslBinderCustomizer<QUsers> {

    @Override
    default void customize(final QuerydslBindings bindings, final QUsers qUsers) {
        bindings.excludeUnlistedProperties(true);
        // 自訂需要 filter 的屬性
        bindings.including(qUsers.name, qUsers.age);
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

    Page<Users> findByName(String name, Pageable pageable);
}
