package com.management.delivery.web.user;

import com.management.delivery.dtos.user.UserInfoRequest;
import com.management.delivery.dtos.user.UserInfoResponse;
import com.management.delivery.entities.user.UserInfo;
import com.management.delivery.service.user.UserInfoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserInfoService userInfoService;
    @Override
    public ResponseEntity<UserInfoResponse> addUser(UserInfoRequest userInfoRequest) {
        return ResponseEntity.ok(userInfoService.addUser(userInfoRequest));
    }

    @Override
    public ResponseEntity<List<UserInfoResponse>> getAllUser() {
        return ResponseEntity.ok(userInfoService.listUser());
    }

    @Override
    public ResponseEntity<UserInfoResponse> getUserById(String userId) {
        return ResponseEntity.ok(userInfoService.getUserById(userId));
    }

    @Override
    public ResponseEntity<UserInfoResponse> updateUserById(String userId, UserInfoRequest userInfoRequest) {
        return ResponseEntity.ok(userInfoService.updateUser(userId, userInfoRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String userId) {
        userInfoService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<UserInfoResponse>> searchUsersWithCriteria(Pageable pageable,String username, String email) {
        return ResponseEntity.ok(userInfoService.searchUsersWithCriteria(pageable,username, email));
    }

}
