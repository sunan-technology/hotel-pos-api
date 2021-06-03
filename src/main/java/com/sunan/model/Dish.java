package com.sunan.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
@Table(name = "dish")
public class Dish implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int dishId;

	@Column(name = "dish_name")
	private String dishName;

	@Column(name = "rate")
	private double rate;

	@Column(name = "non_ac_rate")
	private double nonAcRate;

	@Column(name = "hd_rate")
	private double hdRate;

	@Column(name = "ta_rate")
	private double taRate;

	@Column(name = "eb_rate")
	private double ebRate;

	@Column(name = "z_rate")
	private double zRate;

	@Column(name = "u_rate")
	private double uRate;

	@Column(name = "express_rate")
	private double expressRate;

	@Column(name = "discount")
	private double discount;

	@Column(name = "barcode")
	private String barcode;
	
	@Column(name = "status")
	private String status; //active ,inActive

	@Column(name = "is_active")
	private String isActive;

	@JoinColumn(name = "category_id")
	@ManyToOne
	Category category;

	@JoinColumn(name = "kitchen_id")
	@ManyToOne
	Kitchen kitchen;
	
	@JoinColumn(name = "variation_id")
	@ManyToOne
	Variation variation;

	public Dish(int dishId) {
		super();
		this.dishId = dishId;
	}

	public Dish(List<Dish> dish) {
		// TODO Auto-generated constructor stub
	}

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
	
	public Dish(String dishName) {
		super();
		this.dishName = dishName;
	}
	
	

}
