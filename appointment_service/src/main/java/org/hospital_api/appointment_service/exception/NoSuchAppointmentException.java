package org.hospital_api.appointment_service.exception;

import java.util.NoSuchElementException;

public class NoSuchAppointmentException extends NoSuchElementException {
    public NoSuchAppointmentException() {
        super("No appointment with the provided identifier was found");
    }
}
