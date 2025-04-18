package org.hospital_api.appointment_service.exception;

import java.util.NoSuchElementException;

public class NoSuchClientException extends NoSuchElementException {
    public NoSuchClientException() {
        super("No client with the provided identifier was found");
    }
}
