package com.assessment.eoh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.eoh.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
