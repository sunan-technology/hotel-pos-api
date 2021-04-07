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
@Table(name = "temp_orderedproductkot")
public class TempOrderedProductKOT implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@JoinColumn(name = "ticketid")
	@ManyToOne
	private TempOrderInfoKOT tempOrderInfoKOT;

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
