package org.hospital_api.appointment_service.controller;

import org.hospital_api.appointment_service.dto.AppointmentReqDto;
import org.hospital_api.appointment_service.dto.AppointmentRespDto;
import org.hospital_api.appointment_service.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentReqDto body){
        String response = appointmentService.createAppointment(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<AppointmentRespDto> rescheduleAppointment(@RequestBody AppointmentReqDto body,
                                                                    @PathVariable Long id){
        AppointmentRespDto response = appointmentService.rescheduleAppointment(body, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}") ResponseEntity<String> cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return new ResponseEntity<>("Your appointment was canceled successfully", HttpStatus.OK);
    }

    @GetMapping("/booked/{patientId}") ResponseEntity<List<AppointmentRespDto>> getBookedAppointmentsForPatient(@PathVariable Long patientId){
        return new ResponseEntity<>(appointmentService.getAllBookedAppointmentsForPatient(patientId), HttpStatus.OK);
    }

}
