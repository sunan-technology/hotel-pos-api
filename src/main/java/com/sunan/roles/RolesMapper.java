package com.sunan.roles;

import org.springframework.stereotype.Component;

import com.sunan.model.Roles;

@Component
public class RolesMapper {
	
	public Roles getRolesBuilder(RolesDto dto) {
		
		return Roles.builder()
				.id(dto.getId())
				.role(dto.getRole())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public RolesDto getRolesDtoBuilder(Roles roles) {
		
		return RolesDto.builder()
				.id(roles.getId())
				.role(roles.getRole())
				.isActive(roles.getIsActive())
				.build();
	}

}
