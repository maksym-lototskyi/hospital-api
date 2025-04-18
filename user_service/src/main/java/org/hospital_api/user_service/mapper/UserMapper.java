package org.hospital_api.user_service.mapper;

import org.hospital_api.user_service.dto.UserReqDto;
import org.hospital_api.user_service.dto.UserRespDto;
import org.hospital_api.user_service.model.Role;
import org.hospital_api.user_service.model.User;

public class UserMapper {
    public static UserRespDto userToUserRespDto(User user){
        return UserRespDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User userReqDtoToUser(UserReqDto userReqDto, Role role){
        return User.builder()
                .email(userReqDto.getEmail())
                .role(role)
                .surname(userReqDto.getSurname())
                .name(userReqDto.getName())
                .phoneNumber(userReqDto.getPhoneNumber())
                .build();
    }
}
