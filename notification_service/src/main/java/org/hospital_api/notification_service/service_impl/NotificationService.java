package org.hospital_api.notification_service.service_impl;

import lombok.RequiredArgsConstructor;
import org.hospital_api.notification_service.event.AppointmentNotificationEvent;
import org.hospital_api.notification_service.event.BillingEvent;
import org.hospital_api.notification_service.model.EmailRecipient;
import org.hospital_api.notification_service.service.MessageSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final MessageSender messageSender;

    @KafkaListener(topics = "appointment-created", groupId = "id")
    public void sendNotification(AppointmentNotificationEvent appointmentNotificationEvent){
        String content = "THIS IS AN AUTOMATICALLY GENERATED MESSAGE.\n" +
                        "PLEASE, DO NOT RESPOND TO IT!\n\n" +
                        "Dear, " + appointmentNotificationEvent.getPatientName() + ".\n" +
                        "We are happy to notify you that your appointment was booked successfully!\n" +
                        "Doctor " + appointmentNotificationEvent.getDoctorName() + " will await for your arrival at " + appointmentNotificationEvent.getAppointmentTime().toString() +
                        "\nThe contact information of the doctor:\n" +
                        appointmentNotificationEvent.getDoctorEmail() + "\n" + appointmentNotificationEvent.getDoctorPhoneNumber();

        String subject = "Appointment scheduled";
        messageSender.sendMessage(subject, new EmailRecipient(appointmentNotificationEvent.getPatientEmail()), content);
    }
    @KafkaListener(topics = "billing-succeed", groupId = "id")
    public void sendNotificationBilling(BillingEvent billingEvent){
        System.out.println("Notification sent. " + billingEvent.getAppointmentId() + " money withdrawn " + billingEvent.getFees());
    }
}
