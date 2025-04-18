package org.hospital_api.notification_service.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailRecipient implements RecipientDetails{
    private final String email;
    @Override
    public String getContactInformation() {
        return email;
    }
}
