package org.hospital_api.appointment_service.exception;

import java.util.NoSuchElementException;

public class NoSuchDoctorException extends NoSuchElementException {
    public NoSuchDoctorException() {
        super("Could not find the doctor with the provided identifier");
    }
}
