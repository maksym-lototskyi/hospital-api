package org.hospital_api.billing_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long appointmentId;
    private BigDecimal fees;
    @Enumerated
    private BillingStatus billingStatus;
}
