package com.sunan.user.login;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	
	
	public UserDto(String username, String email, List<String> roles) {
		this.token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJYWFgiLCJpYXQiOjE1OTc1NTg0NDYsImV4cCI6MTU5NzY0NDg0Nn0.InrW23sUzpfxcP9qEOJsLzsIz51SYiPOq2TbsAD_mqEY_a9UGPl_a7HwsLxrXCP79hOq03IPFqHBdbvqbJcRrg";
		this.id = 1L;
		this.username = username;
		this.email = email;
		this.roles = roles;

	}

}
