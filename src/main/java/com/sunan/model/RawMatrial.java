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
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rawmatrial")
public class RawMatrial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "purchaseunit")
	private String purchaseUnit;

	@Column(name = "consumptionunit")
	private String consumptionUnit;

	@Column(name = "purchaseprice")
	private Double purchasePrice;

	@Column(name = "saleprice")
	private Double salePrice;

//	@Column(name = "taxtype")
//	private String taxType; // GST/VAT

//	@Column(name = "taxamount")
//	private Double taxAmount; // GST(%) /ExciseDuty(amount)
	
	@JoinColumn(name = "taxes_id")
	@ManyToOne
	private Taxes taxes;
	
//	@Column(name = "excise_duty")
//	private Double exciseDuty;

	@JoinColumn(name = "category_id")
	@ManyToOne
	Category category;

	@Column(name = "normalloss")
	private Double normallLoss;

	@Column(name = "hsncode")
	private String hsnCode;

	@Column(name = "minimum_stocklevel")
	private Double ministockLevel;

	@Column(name = "minimum_stocklevel_unit")
	private String ministocklevelUnit;

	@Column(name = "at_per_stocklevel")
	private Double atperstockLevel;

	@Column(name = "at_per_stocklevel_unit")
	private String atperstocklevelUnit;

	@Column(name = "description")
	private String description;

	@Column(name = "closing_stock_calcuation")
	private String closingStockCalculation; // daily,weekly

	@Column(name = "isprivate")
	private String isPrivate; // yes/no

	@Column(name = "is_expiry")
	private String isExpiry; // yes/no

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

	public RawMatrial(int id) {
		super();
		this.id = id;
	}
	
	

}
