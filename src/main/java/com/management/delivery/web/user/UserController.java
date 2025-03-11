package com.management.delivery.web.user;

import com.management.delivery.dtos.user.UserInfoRequest;
import com.management.delivery.dtos.user.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    String URI_USER = "/user";
    String USER_ID = "/{userId}";
    String PATH_VARIABLE_USER_ID = "userId";
    String URI_SEARCH = "/search";

    @PostMapping(value = URI_USER)
    ResponseEntity<UserInfoResponse> addUser(@RequestBody UserInfoRequest userInfoRequest) ;

    @GetMapping
    ResponseEntity<List<UserInfoResponse>> getAllUser();

    @GetMapping(value = USER_ID)
    ResponseEntity<UserInfoResponse> getUserById(@PathVariable(name = PATH_VARIABLE_USER_ID) String userId);

    @PatchMapping(value = USER_ID)
    ResponseEntity<UserInfoResponse>  updateUserById(@PathVariable(name = PATH_VARIABLE_USER_ID) String userId,@RequestBody UserInfoRequest userInfoRequest);

    @DeleteMapping(value = USER_ID)
    ResponseEntity<Void> deleteUserById(@PathVariable(name = PATH_VARIABLE_USER_ID) String userId);

    @GetMapping(value = URI_SEARCH)
    ResponseEntity<Page<UserInfoResponse>> searchUsersWithCriteria(@PageableDefault(page = 0, size = 10) Pageable pageable, @RequestParam(required = false) String username ,@RequestParam(required = false) String email);


 }
