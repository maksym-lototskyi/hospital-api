package org.hospital_api.billing_service.repository;

import org.hospital_api.billing_service.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
