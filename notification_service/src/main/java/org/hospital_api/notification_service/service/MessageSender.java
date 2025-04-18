package org.hospital_api.notification_service.service;

import org.hospital_api.notification_service.model.RecipientDetails;

public interface MessageSender {
    void sendMessage(String subject, RecipientDetails recipientDetails, String content);
}
