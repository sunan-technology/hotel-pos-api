package com.sunan.kitchen.raw.material;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KitchenRawMatrialDto {

	private int id;

	private String rawMatrialName;

	private int quantity;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date transferDate;

	private int unitsId;
	
	private int kitchenId;

	private String isActive;

}
