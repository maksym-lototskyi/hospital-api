package org.hospital_api.appointment_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentReqDto {
   @NotNull
   private Long doctorId;
   @NotNull
   private Long patientId;
   @NotNull
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
   private LocalDateTime appointmentTime;
}
