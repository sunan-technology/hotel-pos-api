package com.sunan.employee.registration;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegistrationDto {

	private int id;

	private String employeeId;

	private String employeeName;

	private String address;

	private String city;

	private String contactNo;

	private String email;

	private Date dateOfJoining;

	private String photo;

	private String isActive;

}
