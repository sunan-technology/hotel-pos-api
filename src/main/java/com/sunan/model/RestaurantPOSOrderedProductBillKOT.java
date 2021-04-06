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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurantpos_orderedproductbillkot")
public class RestaurantPOSOrderedProductBillKOT extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "bill_id")
	@ManyToOne
	private RestaurantPOSBillingInfoKOT restaurantPOSBillingInfoKOT;

	@Column(name = "dish")
	private String dish;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "vatper")
	private Double vatPer;

	@Column(name = "vatamount")
	private Double vatAmount;

	@Column(name = "stper")
	private Double stPer;

	@Column(name = "stamount")
	private Double stAmount;

	@Column(name = "scper")
	private Double scPer;

	@Column(name = "scamount")
	private Double scAmount;

	@Column(name = "discountper")
	private Double discountPer;

	@Column(name = "discountamount")
	private Double discountAmount;

	@Column(name = "totalamount")
	private Double totalAmount;

	@JoinColumn(name = "table_id")
	@ManyToOne
	private HotelTable hotelTable;

	@Column(name = "groupname")
	private String groupName;

	@Column(name = "ticketno")
	private String ticketNo;

	@Column(name = "rcost")
	private Double rCost;

	@Column(name = "isactive")
	private String isActive;

	
}