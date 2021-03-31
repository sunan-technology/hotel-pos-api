package com.sunan.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "purchase_join")
public class PerchaseJoin extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "purchase_id")
	@ManyToOne
	private Purchase purchase;

	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private Double price;

	@Column(name = "total_amount")
	private Double totalAmount;

	@JoinColumn(name = "storagetype_id")
	@ManyToOne
	private StorageType storageType;

	@JoinColumn(name = "warehouses_id")
	@ManyToOne
	private Warehouses warehouses;

	@Column(name = "hasexpiry_date")
	private int hasExpiryDate;

	@Column(name = "expriy_date")
	private Date expiryDate;

	@Column(name = "is_active")
	private String isActive;

}
