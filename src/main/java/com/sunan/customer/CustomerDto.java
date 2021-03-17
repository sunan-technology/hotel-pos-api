package com.sunan.customer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private int id;

	private String name;

	private String address;

	private String contactNo;

	private String email;

	private Date birthDate;

	private Date anniversaryDate;

	private Date registrationDate;

	private String isActive;

}
