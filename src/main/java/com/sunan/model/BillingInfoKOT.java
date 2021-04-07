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
@Table(name = "billinginfokot")
public class BillingInfoKOT  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "billno")
	private String billNO;

	@Column(name = "ODN")
	private String ODN;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "billdate")
	private Date billDate;

	@Column(name = "kot_discountper")
	private Double kotDiscountPer;

	@Column(name = "kot_discountamount")
	private Double KotDiscountAmount;

	@Column(name = "grandtotal")
	private Double grandTotal;

	@Column(name = "cash")
	private Double cash;

	@Column(name = "change_amt")
	private Double change;

	@Column(name = "operator")
	private String operator;

	@Column(name = "paymentmode")
	private String paymentMode;

	@Column(name = "exchangerate")
	private Double exchangeRate;

	@Column(name = "currencycode")
	private String currencyCode;

	@Column(name = "discountreason")
	private String discountReason;

	@JoinColumn(name = "member_id")
	@ManyToOne
	private Member member;

	@Column(name = "loyaltypoints")
	private int loyaltyPoints;

	@Column(name = "loyaltyamount")
	private Double loyaltyAmount;

	@Column(name = "roundoff")
	private Double roundOff;

	@JoinColumn(name = "customer_id")
	@ManyToOne
	private Customer customer;

	@Column(name = "di_status")
	private String di_Status;

	@Column(name = "isactive")
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
	
	public BillingInfoKOT(int id) {
		super();
		this.id = id;
	}

	
//	public RestaurantPOSBillingInfoKOT() {
//
//	}

//	@Builder
//	public RestaurantPOSBillingInfoKOT(Hotel hotelId, Timestamp createdAt, Date updatedAt, int id, String billNO,
//			String oDN, Date billDate, Double kotDiscountPer, Double kotDiscountAmount, Double grandTotal, Double cash,
//			Double change, String operator, String paymentMode, Double exchangeRate, String currencyCode,
//			String discountReason, Member member, int loyaltyPoints, Double loyaltyAmount, Double roundOff,
//			Customer customer, String di_Status, String isActive) {
//		super(hotelId, createdAt, updatedAt);
//		this.id = id;
//		this.billNO = billNO;
//		ODN = oDN;
//		this.billDate = billDate;
//		this.kotDiscountPer = kotDiscountPer;
//		KotDiscountAmount = kotDiscountAmount;
//		this.grandTotal = grandTotal;
//		this.cash = cash;
//		this.change = change;
//		this.operator = operator;
//		this.paymentMode = paymentMode;
//		this.exchangeRate = exchangeRate;
//		this.currencyCode = currencyCode;
//		this.discountReason = discountReason;
//		this.member = member;
//		this.loyaltyPoints = loyaltyPoints;
//		this.loyaltyAmount = loyaltyAmount;
//		this.roundOff = roundOff;
//		this.customer = customer;
//		this.di_Status = di_Status;
//		this.isActive = isActive;
//	}

}
