package com.assessment.eoh.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String client;

	private Long vatRate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm a")
	@CreationTimestamp
	private Date invoiceDate;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<LineItem> lineItems = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Invoice() {}
	public BigDecimal getSubTotal() {
		BigDecimal lineItemTotal = new BigDecimal(0);

		for (LineItem myClass : lineItems) {
			lineItemTotal = myClass.getLineItemTotal();
		}

		return lineItemTotal;
	}

	public BigDecimal getVat() {

		BigDecimal vatAmount = new BigDecimal(0);

		BigDecimal lineItemTotal = new BigDecimal(0);

		double vatRatePercentage = ((double) vatRate / 100);

		for (LineItem myClass : lineItems) {
			myClass.getLineItemTotal();
			lineItemTotal = myClass.getLineItemTotal();
			BigDecimal vatRatePercentageToBigDecimal = new BigDecimal(vatRatePercentage);
			vatAmount = lineItemTotal.multiply(vatRatePercentageToBigDecimal).setScale(2, RoundingMode.HALF_UP);
		}

		return vatAmount;
	}

	public BigDecimal getTotal() {
		BigDecimal lineItemTotal = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);

		for (LineItem myClass : lineItems) {
			BigDecimal vatAmount = getVat();
			myClass.getLineItemTotal();
			lineItemTotal = myClass.getLineItemTotal();
			total = vatAmount.add(lineItemTotal);
		}

		return total;
	}
}
