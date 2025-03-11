package com.management.delivery.repository.user;

import com.management.delivery.entities.user.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> , QuerydslPredicateExecutor<UserInfo> {
    Optional<UserInfo> findByEmail(String email);
}
