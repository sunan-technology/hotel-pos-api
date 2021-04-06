package com.sunan.login;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private List<HotelInfo> hotelInfos;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, List<HotelInfo> hotelInfos) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.hotelInfos = hotelInfos;

	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HotelInfo> getHotelInfos() {
		return hotelInfos;
	}

	public void setHotelInfos(List<HotelInfo> hotelInfos) {
		this.hotelInfos = hotelInfos;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}

class HotelInfo {
	int id;
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HotelInfo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
