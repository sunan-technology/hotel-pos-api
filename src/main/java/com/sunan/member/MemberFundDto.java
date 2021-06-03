package com.sunan.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFundDto {
	
	private int memberId;
	private String name;
	//private int cardNo;
	private Double balance;

}
