package com.sunan.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotels")
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "address_1")
	private String address1;

	@Column(name = "address_2")
	private String address2;

	@Column(name = "address_3")
	private String address3;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "email")
	private String email;

	@Column(name = "tin")
	private String tin;

	@Column(name = "stNo")
	private String stNo;

	@Column(name = "cin")
	private String cin;

	@Column(name = "logo")
	private String logo;

	@Column(name = "base_currency")
	private String baseCurrency;

	@Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "ticket_footer_message")
	private String ticketFooterMessage;

	@Column(name = "show_logo")
	private String showLogo;

	@Column(name = "start_billno")
	private String startBillNo;

	@Column(name = "is_active")
	private String isActive;
	
	@JsonIgnore
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

}
