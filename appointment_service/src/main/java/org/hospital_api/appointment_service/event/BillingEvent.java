package org.hospital_api.appointment_service.event;


public class BillingEvent {
    private Long appointmentId;

    public BillingEvent(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public BillingEvent() {
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
