package org.hospital_api.notification_service.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentNotificationEvent {
    private Long appointmentId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPhoneNumber;
    private String patientEmail;
    private String patientName;
    private LocalDateTime appointmentTime;
}
