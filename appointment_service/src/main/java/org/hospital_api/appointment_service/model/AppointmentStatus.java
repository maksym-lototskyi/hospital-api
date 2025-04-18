package org.hospital_api.appointment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


public enum AppointmentStatus {
    BOOKED, COMPLETED, CANCELED
}
