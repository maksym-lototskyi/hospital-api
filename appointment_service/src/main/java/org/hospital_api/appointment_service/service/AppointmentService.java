package org.hospital_api.appointment_service.service;


import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.hospital_api.appointment_service.dto.AppointmentReqDto;
import org.hospital_api.appointment_service.dto.AppointmentRespDto;
import org.hospital_api.appointment_service.dto.UserDto;
import org.hospital_api.appointment_service.event.AppointmentEvent;
import org.hospital_api.appointment_service.event.BillingEvent;
import org.hospital_api.appointment_service.exception.NoSuchAppointmentException;
import org.hospital_api.appointment_service.exception.NoSuchClientException;
import org.hospital_api.appointment_service.exception.NoSuchDoctorException;
import org.hospital_api.appointment_service.feing_client.UserServiceFeignClient;
import org.hospital_api.appointment_service.mapper.AppointmentMapper;
import org.hospital_api.appointment_service.model.Appointment;
import org.hospital_api.appointment_service.model.AppointmentStatus;
import org.hospital_api.appointment_service.repository.AppointmentRepository;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final KafkaTemplate<String, AppointmentEvent> template;
    private final UserServiceFeignClient feignClient;

    public AppointmentService(AppointmentRepository appointmentRepository, KafkaTemplate<String, AppointmentEvent> template, UserServiceFeignClient feignClient, LoggingRebinder loggingRebinder) {
        this.appointmentRepository = appointmentRepository;
        this.template = template;
        this.feignClient = feignClient;
    }

    public String createAppointment(AppointmentReqDto reqDto){
        feignClient.getUser(reqDto.getDoctorId()).orElseThrow(NoSuchDoctorException::new);
        feignClient.getUser(reqDto.getPatientId()).orElseThrow(NoSuchClientException::new);

        Appointment appointment = AppointmentMapper.appointmentReqDtoToAppointment(reqDto);
        appointmentRepository.save(appointment);
        template.send("appointment-registered", new AppointmentEvent(appointment.getId()));
        log.info("Message sent to kafka");

        return "Your submission was accepted successfully";
    }

    @KafkaListener(topics = "billing-failed", groupId = "group_id")
    public void failedPaymentListener(BillingEvent event){
        removeAppointment(event.getAppointmentId());
        log.info("Billing failed. Appointment with id " + event.getAppointmentId() + " removed");
    }

    @KafkaListener(topics = "billing-succeed", groupId = "group_id")
    public void paymentSucceed(BillingEvent event){
        System.out.println("appointment was created successfully");
        Appointment appointment = appointmentRepository.findById(event.getAppointmentId()).orElseThrow(NoSuchAppointmentException::new);
        UserDto doctor = feignClient.getUser(appointment.getDoctorId()).orElseThrow(NoSuchDoctorException::new);
        UserDto patient = feignClient.getUser(appointment.getPatientId()).orElseThrow(NoSuchClientException::new);
        template.send("appointment-created", AppointmentMapper.appointmentToAppointmentNotificationEvent(appointment, doctor, patient));
    }

    @Transactional
    public AppointmentRespDto rescheduleAppointment(AppointmentReqDto reqDto, Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(NoSuchAppointmentException::new);
        UserDto doctor = feignClient.getUser(appointment.getDoctorId()).orElseThrow(NoSuchDoctorException::new);
        appointment.setAppointmentDateTime(reqDto.getAppointmentTime());
        return AppointmentMapper.appointmentToAppointmentRespDto(appointment, doctor);
    }

    private void removeAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
    @Transactional
    public void cancelAppointment(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(NoSuchAppointmentException::new);
        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);
    }

    public List<AppointmentRespDto> getAllBookedAppointmentsForPatient(Long patientId){

        return appointmentRepository.getAppointmentByAppointmentStatusAndPatientId(AppointmentStatus.BOOKED, patientId)
                .stream()
                .map(
                        (appointment -> {
                            UserDto doctor = feignClient.getUser(appointment.getDoctorId()).orElseThrow(NoSuchDoctorException::new);
                            return AppointmentMapper.appointmentToAppointmentRespDto(appointment, doctor);
                        })
                )
                .collect(Collectors.toList());
    }
}
