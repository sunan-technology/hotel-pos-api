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
@Table(name = "internaltransfer")
public class InternalTransfer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	
	@JoinColumn(name = "kitchen_id")
	@ManyToOne
	private Kitchen kitchen;
	
	@Column(name = "mrn_no")
	private String mrnNo;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;
	
	@Column(name = "invoiceno")
	private String invoiceNo;
	
	@Column(name = "deliverycharges_ininvoice")
	private Double deliveryChargesInInvoice;
	
	@Column(name = "rawmatrial_amounttotal")
	private Double rawMatrialAmountTotal;
	
	@Column(name = "cgst")
	private int cgst;
	
	@Column(name = "sgst")
	private int sgst;
	
	@Column(name = "igst")
	private int igst;
	
	@Column(name = "subtotal")
	private Double subTotal;
	
	@Column(name = "grand_total")
	private Double grandTotal;
	
	@Column(name = "discounttype")
	private String discountType;
	
	@Column(name = "discount_ininvoice")
	private Double discountInInvoice;
	
	@Column(name = "discountper")
	private int discountPer;
	
	@Column(name = "total_deliverycharges")
	private Double totalDeliveryCharges;
	
	@Column(name = "total_discount")
	private Double totalDiscount;
	
	@Column(name = "taxcollected_atsource")
	private Double taxCollectedAtSource;
	
	@Column(name = "totaltaxcollected_atsource")
	private Double totalTaxCollectedAtSource;
	
	@Column(name = "payment_type")
	private String paymentType; //paid,unpaid
	
	@Column(name = "taxpayable_underreversecharge")
	private String taxPayableUnderReverseCharge;  //yes,no
	
	@Column(name = "paidamount")
	private Double paidAmount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "paymentdate")
	private Date paymentDate;
	
	@Column(name = "payment_referenceno")
	private String paymentReferenceNo;
	
	@Column(name = "payment_mode")
	private String paymentMode;  //cash,card,cheque,online,other

	@Column(name = "bankname")
	private String bankName;
	
	@Column(name = "bankbranch")
	private String bankBranch;
	
	@Column(name = "ifsc_code")
	private String ifscCode;
	
	@Column(name = "accountno")
	private String accountNo;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "termsandconditions")
	private String termsAndConditions;
	
	@Column(name = "update_inventorystock")
	private String updateInventoryStock;
	
	@Column(name = "editable")
	private String editable;
	
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

	public InternalTransfer(int id) {
		super();
		this.id = id;
	}

	
	

}
