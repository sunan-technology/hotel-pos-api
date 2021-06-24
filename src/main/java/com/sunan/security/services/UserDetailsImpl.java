package com.sunan.security.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunan.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;
	
	private String firstName;
	
	private String lastName;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities, String firstName, String lastName) {
		this.id = Long.parseLong(String.valueOf(id));
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public static UserDetailsImpl build(User user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
//				.collect(Collectors.toList());
		List<GrantedAuthority> authorities =  Arrays.asList(new SimpleGrantedAuthority(user.getRoles().getRole()));
		return new UserDetailsImpl(
				user.getId(), 
				user.getUserName(), 
				user.getPassword(), 
				authorities,
				user.getName(), user.getEmail());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
