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
@Table(name = "supplier")
public class Supplier  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "tin")
	private String tin;

	@Column(name = "st_no")
	private String stNo;

	@Column(name = "cst")
	private String cst;

	@Column(name = "pan")
	private String pan;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "bank")
	private String bank;

	@Column(name = "branch")
	private String branch;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "opening_balance")
	private Double openingBalance;

	@Column(name = "opening_balancetype")
	private String openingBalanceType;

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
	
	public Supplier(int supplierId) {
		super();
		this.supplierId = supplierId;
	}

}
