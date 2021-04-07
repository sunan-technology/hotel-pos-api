package com.sunan.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "temp_orderinfokot")
public class TempOrderInfoKOT implements Serializable {

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

	
	
	@JoinColumn(name = "table_id")
	@ManyToOne
	private HotelTable hotelTable;

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
	
//	@OneToMany(mappedBy = "tempOrderInfoKOT", cascade = CascadeType.ALL)
//	Set<TempOrderedProductKOT> tempOrderedProductKOTs;
	
	public TempOrderInfoKOT(int id) {
		super();
		this.id = id;
	}
	
	 
}
