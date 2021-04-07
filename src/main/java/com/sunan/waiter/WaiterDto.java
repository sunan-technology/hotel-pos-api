package com.sunan.waiter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaiterDto {

	private int id;

	private String name;

	private String mobileNo;

	private String email;

	private String adharCard;

	private String address;

	private String city;

	private String state;

	private String isActive;

}
