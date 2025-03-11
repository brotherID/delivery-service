package com.management.delivery.service.user;

import com.management.delivery.dtos.user.UserInfoRequest;
import com.management.delivery.dtos.user.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserInfoService {
    UserInfoResponse addUser(UserInfoRequest userInfoRequest);
    List<UserInfoResponse> listUser();
    void deleteUserById(String userId);
    UserInfoResponse getUserById(String userId);
    UserInfoResponse updateUser(String userId ,UserInfoRequest userInfoRequest);
    Page<UserInfoResponse> searchUsersWithCriteria(Pageable pageable,String username, String email);
}
