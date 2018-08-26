package com.assessment.eoh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.eoh.model.Invoice;
import com.assessment.eoh.repository.InvoiceRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	public Invoice save(@Valid Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	public Optional<Invoice> getInvoiceById(Long id) {
		return invoiceRepository.findById(id);
	}

	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

}