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
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dish")
public class Dish  implements Serializable{
	
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
	
	@Column(name = "is_active")
	private String isActive;
	

	@JoinColumn(name = "category_id")
	@ManyToOne
	Category category;
	
	@JoinColumn(name = "kitchen_id")
	@ManyToOne
	Kitchen kitchen;

}
