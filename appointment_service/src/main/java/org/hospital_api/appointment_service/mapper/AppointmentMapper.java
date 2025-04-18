package org.hospital_api.appointment_service.mapper;

import org.hospital_api.appointment_service.dto.AppointmentReqDto;
import org.hospital_api.appointment_service.dto.AppointmentRespDto;
import org.hospital_api.appointment_service.dto.UserDto;
import org.hospital_api.appointment_service.event.AppointmentNotificationEvent;
import org.hospital_api.appointment_service.model.Appointment;
import org.hospital_api.appointment_service.model.AppointmentStatus;

public class AppointmentMapper {
    public static Appointment appointmentReqDtoToAppointment(AppointmentReqDto appointmentReqDto){
        return Appointment.builder()
                .patientId(appointmentReqDto.getPatientId())
                .doctorId(appointmentReqDto.getDoctorId())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .appointmentDateTime(appointmentReqDto.getAppointmentTime())
                .build();
    }

    public static AppointmentRespDto appointmentToAppointmentRespDto(Appointment appointment, UserDto doctor){
        return AppointmentRespDto.builder()
                .appointmentId(appointment.getId())
                .appointmentStatus(appointment.getAppointmentStatus().toString())
                .time(appointment.getAppointmentDateTime())
                .doctorName(doctor.getName())
                .doctorEmail(doctor.getEmail())
                .doctorPhoneNumber(doctor.getPhoneNumber())
                .build();
    }

    public static AppointmentNotificationEvent appointmentToAppointmentNotificationEvent(Appointment appointment, UserDto doctor, UserDto patient){
        return AppointmentNotificationEvent.builder()
                .patientEmail(patient.getEmail())
                .doctorEmail(doctor.getEmail())
                .doctorName(doctor.getName())
                .doctorPhoneNumber(doctor.getPhoneNumber())
                .appointmentTime(appointment.getAppointmentDateTime())
                .patientName(patient.getName())
                .build();
    }
}
