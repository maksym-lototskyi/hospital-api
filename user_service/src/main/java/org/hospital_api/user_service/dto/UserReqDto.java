package org.hospital_api.user_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserReqDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String roleName;
}
