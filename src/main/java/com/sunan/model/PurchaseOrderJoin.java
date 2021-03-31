package com.sunan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchaseorder_join")
public class PurchaseOrderJoin extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "purchaseorder_id")
	@ManyToOne
	private PurchaseOrder purchaseOrder;

	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "priceper_unit")
	private Double pricePerUnit;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "is_active")
	private String isActive;

}
