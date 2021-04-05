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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "restaurantpos_orderinfokot")
public class RestaurantPOSOrderInfoKOT extends BaseEntity implements Serializable {

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
	
	@Column(name = "istemp_invoicegenerated")
	private String isTempInvoiceGenerated;
	
	@Column(name = "no_oftimes")
	private int noOfTimes;

	@Column(name = "kottype")
	private String kotType;

	@Column(name = "is_active")
	private String isActive;

	public RestaurantPOSOrderInfoKOT(int id) {
		super();
		this.id = id;
	}

	
	public RestaurantPOSOrderInfoKOT() {
		
	}

    @Builder
	public RestaurantPOSOrderInfoKOT(Hotel hotelId, Timestamp createdAt, Date updatedAt, int id, String ticketNo,
			Date billDate, Double grandTotal, HotelTable hotelTable, String groupName, String operator,
			String ticketNote, String waiter, String kotStatus, String isEditable, String isTempInvoiceGenerated,
			int noOfTimes, String kotType, String isActive) {
		super(hotelId, createdAt, updatedAt);
		this.id = id;
		this.ticketNo = ticketNo;
		this.billDate = billDate;
		this.grandTotal = grandTotal;
		this.hotelTable = hotelTable;
		this.groupName = groupName;
		this.operator = operator;
		this.ticketNote = ticketNote;
		this.waiter = waiter;
		this.kotStatus = kotStatus;
		this.isEditable = isEditable;
		this.isTempInvoiceGenerated = isTempInvoiceGenerated;
		this.noOfTimes = noOfTimes;
		this.kotType = kotType;
		this.isActive = isActive;
	}


	
	
	

}
