package com.sunan.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int id;

	private String name;
	
	private String username;
	
	private String password;

	private String address;

	private String city;

	private String contactNo;

	private String email;

	private Date dateOfJoining;

	private String photo;
	
	private int rolesId;
	
	private String role;

	private String isActive;

}
