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
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contact_no")
	private String contactNo;
	
	@Column(name = "email")
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "anniversary_date")
	private Date anniversaryDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "registeration_date")
	private Date registrationDate;

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
	
	public Customer(int id) {
		super();
		this.id = id;
	}

}
