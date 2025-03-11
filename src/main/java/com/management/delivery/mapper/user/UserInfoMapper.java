package com.management.delivery.mapper.user;

import com.management.delivery.dtos.user.UserInfoRequest;
import com.management.delivery.dtos.user.UserInfoResponse;
import com.management.delivery.entities.user.UserInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserInfo toUserInfo(UserInfoRequest userInfoRequest);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    UserInfoResponse toUserInfoResponse(UserInfo userInfo);

    List<UserInfoResponse> toListUserInfoResponse(List<UserInfo> userInfos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserInfo userInfo, UserInfoRequest userInfoRequest);
}
