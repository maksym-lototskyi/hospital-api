package org.hospital_api.appointment_service.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentNotificationEvent extends AppointmentEvent{
    private String doctorName;
    private String doctorEmail;
    private String doctorPhoneNumber;
    private String patientEmail;
    private String patientName;
    private LocalDateTime appointmentTime;
}
