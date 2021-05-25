package com.sunan.member;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	

	private int memberId;

	private String name;
	
	private String email;

	private int cardNo;

	private String contactNo;

	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registerationDate;

	private String isActive;

}
