package org.hospital_api.appointment_service.repository;

import org.hospital_api.appointment_service.model.Appointment;
import org.hospital_api.appointment_service.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> getAppointmentByAppointmentStatusAndPatientId(AppointmentStatus appointmentStatus, Long patientId);
}
