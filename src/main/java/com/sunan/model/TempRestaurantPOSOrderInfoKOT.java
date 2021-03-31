package com.sunan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "temprestaurantpos_orderinfokot")
public class TempRestaurantPOSOrderInfoKOT extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "ticketno")
	private String ticketNo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "bill_date")
	private Date billDate;

	@Column(name = "grandtotal")
	private Double grandTotal;

	@Column(name = "tableno")
	private String tableNo;

	@Column(name = "groupname")
	private String groupName;

	@Column(name = "operator")
	private String operator;

	@Column(name = "ticketnote")
	private String ticketNote;

	@Column(name = "waiter")
	private String waiter;

	@Column(name = "kotstatus")
	private String kotStatus;

	@Column(name = "iseditable")
	private String isEditable;

	@Column(name = "kottype")
	private String kotType;

	@Column(name = "is_active")
	private String isActive;

	public TempRestaurantPOSOrderInfoKOT(int id) {
		super();
		this.id = id;
	}
	
	

}