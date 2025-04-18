package org.hospital_api.billing_service.event;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingEvent {
    private Long appointmentId;
    private double fees;
}
