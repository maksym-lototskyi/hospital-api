package org.hospital_api.appointment_service.event;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEvent {
    private Long appointmentId;
}
