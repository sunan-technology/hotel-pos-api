package com.sunan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
public class Supplier extends BaseEntity implements Serializable {

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

	public Supplier(int supplierId) {
		super();
		this.supplierId = supplierId;
	}

}
