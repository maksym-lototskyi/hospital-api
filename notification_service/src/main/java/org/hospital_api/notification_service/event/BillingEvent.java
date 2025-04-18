package org.hospital_api.notification_service.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingEvent {
    private Long appointmentId;
    private double fees;

}
