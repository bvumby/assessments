package com.assessment.eoh.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long quantity;

	private String description;

	private BigDecimal unitPrice;

	@ManyToOne
	private Invoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public LineItem() {}
	
	public BigDecimal getLineItemTotal() {
		BigDecimal longQuantity = new BigDecimal(quantity);
		BigDecimal lineItemTotal = longQuantity.multiply(unitPrice).setScale(2, RoundingMode.HALF_UP);
		return lineItemTotal;
	}

}
