package org.hospital_api.billing_service.service;


import org.hospital_api.billing_service.event.AppointmentEvent;
import org.hospital_api.billing_service.event.BillingEvent;
import org.hospital_api.billing_service.repository.BillingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    private final BillingRepository billingRepository;
    private final KafkaTemplate<String, BillingEvent> kafkaTemplate;

    public BillingService(BillingRepository billingRepository, KafkaTemplate<String, BillingEvent> kafkaTemplate) {
        this.billingRepository = billingRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "appointment-registered", groupId = "group_id", containerFactory = "listenerContainerFactory")
    public void processBilling(AppointmentEvent event){
        System.out.println("Billing failed. Appointment id: " + event.getAppointmentId());
        kafkaTemplate.send("billing-succeed", new BillingEvent(event.getAppointmentId(), 600.0));
    }


}
