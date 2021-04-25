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
@Table(name = "purchase")
public class Purchase  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "invoice_no")
	private String invoiceNo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "purchase_type")
	private String purchaseType;

	@JoinColumn(name = "supplier_id")
	@ManyToOne
	private Supplier supplier;

	@Column(name = "subtotal")
	private Double subTotal;

	@Column(name = "discount_per")
	private Double discountPer;

	@Column(name = "discount")
	private Double discount;

//	@Column(name = "previous_due")
//	private Double previousDue;
//
//	@Column(name = "freight_charges")
//	private Double freightCharges;
//
//	@Column(name = "other_charges")
//	private Double otherCharges;

	@Column(name = "total")
	private Double total;

	@Column(name = "round_off")
	private Double roundOff;

	@Column(name = "grand_total")
	private Double grandTotal;

//	@Column(name = "total_payment")
//	private Double totalPayment;
//
//	@Column(name = "payment_due")
//	private Double paymentDue;
//
//	@Column(name = "remarks")
//	private String remarks;
	
	@Column(name = "update_inventorystock")
	private String updateInventoryStock;
	
	@Column(name = "gstno")
	private String gstNo;
	
	
	@Column(name = "deliverycharges_ininvoice")
	private Double deliveryChargesInInvoice;
	
	@Column(name = "total_deliverycharges")
	private Double totalDeliveryCharges;
	
	@Column(name = "total_discount")
	private Double totalDiscount;
	
	@Column(name = "discount_type")
	private String discountType;
	
	@Column(name = "payment_type")
	private String paymentType; //paid,unpaid
	
	@Column(name = "payment_mode")
	private String paymentMode;  //cash,card,cheque,online,other
	
	@Column(name = "paidamount")
	private Double paidAmount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "paymentdate")
	private Date paymentDate;
	
	@Column(name = "payment_referenceno")
	private String paymentReferenceNo;
	
	@Column(name = "taxcollected_atsource")
	private Double taxCollectedAtSource;
	
	@Column(name = "totaltaxcollected_atsource")
	private Double totalTaxCollectedAtSource;
	
	@Column(name = "cgst")
	private int cgst;
	
	@Column(name = "sgst")
	private int sgst;
	
	@Column(name = "igst")
	private int igst;
	
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

	public Purchase(int id) {
		super();
		this.id = id;
	}
	
}
