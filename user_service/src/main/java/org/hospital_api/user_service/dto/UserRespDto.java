package org.hospital_api.user_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRespDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
