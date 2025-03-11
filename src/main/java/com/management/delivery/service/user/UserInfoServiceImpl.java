package com.management.delivery.service.user;

import com.management.delivery.dtos.user.UserInfoRequest;
import com.management.delivery.dtos.user.UserInfoResponse;
import com.management.delivery.entities.user.QUserInfo;
import com.management.delivery.entities.user.UserInfo;
import com.management.delivery.mapper.user.UserInfoMapper;
import com.management.delivery.repository.user.UserInfoRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import java.util.List;

import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    public static final String USER_FOUNDED = "User founded";
    public static final String USER_EXIST = "The user with email %s  exist.";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String DETAIL_USER = "User with %s not founded";
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoResponse addUser(UserInfoRequest userInfoRequest) {
        userInfoRepository.findByEmail(userInfoRequest.getEmail())
                .ifPresent(userInfoFound -> {
                    throw Problem.builder()
                            .withTitle(USER_FOUNDED)
                            .withStatus(CONFLICT)
                            .withDetail(String.format(USER_EXIST, userInfoFound.getEmail()))
                            .build();
                });
        //userInfoRequest.setPassword(passwordEncoder.encode(userInfoRequest.getPassword()));
        UserInfo userInfo = userInfoRepository.save(userInfoMapper.toUserInfo(userInfoRequest));
        return userInfoMapper.toUserInfoResponse(userInfo);
    }

    @Override
    public List<UserInfoResponse> listUser() {
        return userInfoMapper.toListUserInfoResponse(userInfoRepository.findAll());
    }

    @Override
    public void deleteUserById(String userId) {
        UserInfo user = userInfoRepository.findById(userId)
                .orElseThrow(
                        ()-> Problem.builder()
                        .withTitle(USER_NOT_FOUND)
                        .withStatus(NOT_FOUND)
                        .withDetail(String.format(DETAIL_USER, userId))
                        .build()
                );
        userInfoRepository.deleteById(userId);
    }

    @Override
    public UserInfoResponse getUserById(String userId) {
        UserInfo user = userInfoRepository.findById(userId)
                .orElseThrow(
                        ()-> Problem.builder()
                                .withTitle(USER_NOT_FOUND)
                                .withStatus(NOT_FOUND)
                                .withDetail(String.format(DETAIL_USER, userId))
                                .build()
                );
        return userInfoMapper.toUserInfoResponse(user);
    }

    @Override
    public UserInfoResponse updateUser(String userId ,UserInfoRequest userInfoRequest) {
        UserInfo user = userInfoRepository.findById(userId)
                .orElseThrow(
                        ()-> Problem.builder()
                                .withTitle(USER_NOT_FOUND)
                                .withStatus(NOT_FOUND)
                                .withDetail(String.format(DETAIL_USER, userId))
                                .build()
                );
        userInfoMapper.updateEntity(user, userInfoRequest);
        return userInfoMapper.toUserInfoResponse(userInfoRepository.save(user));
    }

    @Override
    public Page<UserInfoResponse> searchUsersWithCriteria(Pageable pageable,String username, String email) {
        QUserInfo qUser = QUserInfo.userInfo;
        BooleanBuilder predicate = new BooleanBuilder();

        if (username != null && !username.isEmpty()) {
            predicate.and(qUser.username.containsIgnoreCase(username));
        }

        if (email != null && !email.isEmpty()) {
            predicate.and(qUser.email.containsIgnoreCase(email));
        }
        List<UserInfo> users =  (List<UserInfo>) userInfoRepository.findAll(predicate);
        List<UserInfoResponse> usersResponses = userInfoMapper.toListUserInfoResponse(users);
        return new PageImpl<>(usersResponses, pageable, users.size());
    }

}
