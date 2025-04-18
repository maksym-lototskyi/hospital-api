package org.hospital_api.appointment_service.excpetion_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class AppointmentExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String > handleNoSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
