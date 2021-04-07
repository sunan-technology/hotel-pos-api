package com.sunan.employee.registration;

import org.springframework.stereotype.Component;

import com.sunan.model.EmployeeRegistration;

@Component
public class EmployeeRegistrationMapper {
	
	public EmployeeRegistration getEmployeeRegistrationBuilder(EmployeeRegistrationDto dto) {
		
		return EmployeeRegistration.builder()
				.id(dto.getId())
				.employeeId(dto.getEmployeeId())
				.employeeName(dto.getEmployeeName())
				.address(dto.getAddress())
				.city(dto.getCity())
				.contactNo(dto.getContactNo())
				.email(dto.getEmail())
				.dateOfJoining(dto.getDateOfJoining())
				.photo(dto.getPhoto())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public EmployeeRegistrationDto getEmployeeRegistrationDtoBuilder(EmployeeRegistration employeeRegistration) {
		
		return EmployeeRegistrationDto.builder()
				.id(employeeRegistration.getId())
				.employeeId(employeeRegistration.getEmployeeId())
				.employeeName(employeeRegistration.getEmployeeName())
				.address(employeeRegistration.getAddress())
				.city(employeeRegistration.getCity())
				.contactNo(employeeRegistration.getContactNo())
				.email(employeeRegistration.getEmail())
				.dateOfJoining(employeeRegistration.getDateOfJoining())
				.photo(employeeRegistration.getPhoto())
				.isActive(employeeRegistration.getIsActive())
				.build();
	}

}
