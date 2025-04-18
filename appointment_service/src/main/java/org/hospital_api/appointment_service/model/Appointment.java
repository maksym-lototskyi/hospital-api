package org.hospital_api.appointment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentDateTime;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
}
