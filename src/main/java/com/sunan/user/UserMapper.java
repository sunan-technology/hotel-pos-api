package com.sunan.user;

import org.springframework.stereotype.Component;

import com.sunan.model.User;
import com.sunan.model.Roles;

@Component
public class UserMapper {
	
	public User getUserBuilder(UserDto dto) {
		
		return User.builder()
				.id(dto.getId())
				.name(dto.getName())
				.userName(dto.getUsername())
				.password(dto.getPassword())
				.address(dto.getAddress())
				.city(dto.getCity())
				.contactNo(dto.getContactNo())
				.email(dto.getEmail())
				.dateOfJoining(dto.getDateOfJoining())
				.photo(dto.getPhoto())
				.roles(new Roles(dto.getRolesId()))
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public UserDto getUserDtoBuilder(User user) {
		
		return UserDto.builder()
				.id(user.getId())
			    .name(user.getName())
			    .username(user.getUserName())
				.address(user.getAddress())
				.city(user.getCity())
				.contactNo(user.getContactNo())
				.email(user.getEmail())
				.dateOfJoining(user.getDateOfJoining())
				.photo(user.getPhoto())
				.rolesId(user.getRoles().getId())
				.role(user.getRoles().getRole())
				.isActive(user.getIsActive())
				.build();
	}

}
