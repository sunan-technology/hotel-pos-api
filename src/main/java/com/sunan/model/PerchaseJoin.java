package com.sunan.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_join")
public class PerchaseJoin  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "purchase_id")
	@ManyToOne
	private Purchase purchase;

//	@JoinColumn(name = "product_id")
//	@ManyToOne
//	private Product product;
	
	@Column(name = "rawmatrial_name")
	private String rawMatrialName;

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "purchase_quantity")
	private int purchaseQuantity;

	@Column(name = "price")
	private Double price;

	@Column(name = "total_amount")
	private Double totalAmount;

	@JoinColumn(name = "units_id")
	@ManyToOne
	private Units units;
	
//	@Column(name = "cgst")
//	private int cgst;
//	
//	@Column(name = "sgst")
//	private int sgst;
//	
//	
//	@Column(name = "igst")
//	private int igst;
	
	@JoinColumn(name = "taxes_id")
	@ManyToOne
	private Taxes taxes;
	
	
	@Column(name = "description")
	private String description;

	@JoinColumn(name = "warehouses_id")
	@ManyToOne
	private Warehouses warehouses;

	@Column(name = "hasexpiry_date")
	private int hasExpiryDate;

//	@JsonFormat(pattern = "yyyy-MM-dd")
//	@Temporal(TemporalType.DATE)
//	@Column(name = "purchase_date")
//	private Date purchaseDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "expriy_date")
	private Date expiryDate;
	
	@JoinColumn(name = "rawmatrial_id")
	@ManyToOne
	private RawMatrial rawMatrial;

	@Column(name = "is_active")
	private String isActive;

	@JoinColumn(name = "hotel_id")
	@ManyToOne
	public Hotel hotel;

	@JsonIgnore
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Timestamp createdAt;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date updatedAt;
}
