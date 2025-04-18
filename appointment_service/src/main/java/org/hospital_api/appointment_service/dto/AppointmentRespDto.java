package org.hospital_api.appointment_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AppointmentRespDto {
    private Long appointmentId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPhoneNumber;
    private LocalDateTime time;
    private String appointmentStatus;
}
