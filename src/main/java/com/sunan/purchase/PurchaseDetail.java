package com.sunan.purchase;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {
	int id;
	int quantity;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	private Date expiryDate;
	
}

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class RawMatrialDto{
	int id;
	String name;
	
}

