package com.assessment.eoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.eoh.model.Invoice;
import com.assessment.eoh.service.InvoiceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;


    /**
     * Save Invoice
     */
    @PostMapping("invoices")
    public Invoice saveInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceService.save(invoice);
    }

    /**
     * Get a Invoice by ID
     * @return Invoice
     */
    @GetMapping("invoices/{invoiceId}")
    public Optional<Invoice> getInvoice(@PathVariable("invoiceId") Long id) {
        return invoiceService.getInvoiceById(id);
    }

    /**
     * Get all Invoices
     * @return Invoice
     */
    @GetMapping("invoices")
    public List<Invoice> getAll() {
        return invoiceService.getAllInvoices();
    }

}

