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

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "temprestaurantpos_orderedproductkot")
public class TempRestaurantPOSOrderedProductKOT extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "ticketid")
	@ManyToOne
	private TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT;

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

	@Column(name = "itemstatus")
	private String itemStatus;

	@Column(name = "is_active")
	private String isActive;
	
	
	
	public TempRestaurantPOSOrderedProductKOT() {
		
	}


    @Builder
	public TempRestaurantPOSOrderedProductKOT(Hotel hotelId, Timestamp createdAt, Date updatedAt, int id,
			TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT, String dish, Double rate, int quantity,
			Double amount, Double vatPer, Double vatAmount, Double stPer, Double stAmount, Double scPer,
			Double scAmount, Double discountPer, Double discountAmount, Double totalAmount, HotelTable hotelTable,
			String itemStatus, String isActive) {
		super(hotelId, createdAt, updatedAt);
		this.id = id;
		this.tempRestaurantPOSOrderInfoKOT = tempRestaurantPOSOrderInfoKOT;
		this.dish = dish;
		this.rate = rate;
		this.quantity = quantity;
		this.amount = amount;
		this.vatPer = vatPer;
		this.vatAmount = vatAmount;
		this.stPer = stPer;
		this.stAmount = stAmount;
		this.scPer = scPer;
		this.scAmount = scAmount;
		this.discountPer = discountPer;
		this.discountAmount = discountAmount;
		this.totalAmount = totalAmount;
		this.hotelTable = hotelTable;
		this.itemStatus = itemStatus;
		this.isActive = isActive;
	}



	

	

}
