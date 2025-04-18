package org.hospital_api.appointment_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
