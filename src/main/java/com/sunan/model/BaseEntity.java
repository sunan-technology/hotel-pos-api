package com.sunan.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@MappedSuperclass
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BaseEntity {

	@JoinColumn(name = "hotel_id")
	@ManyToOne
	public Hotel hotelId;

//	@CreationTimestamp
//	public Date creationDate;
//
//	@UpdateTimestamp
//	public Date updationDate;

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

	public BaseEntity(Hotel hotelId, Timestamp createdAt, Date updatedAt) {
		super();
		this.hotelId = hotelId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

}
