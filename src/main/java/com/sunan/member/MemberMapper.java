package com.sunan.member;

import org.springframework.stereotype.Component;

import com.sunan.model.Member;

@Component
public class MemberMapper {
	
	public Member getMemberBuilder(MemberDto dto) {
		
		return Member.builder()
				.memberId(dto.getMemberId())
				.name(dto.getName())
				.cardNo(dto.getCardNo())
				.contactNo(dto.getContactNo())
				.address(dto.getAddress())
				.registerationDate(dto.getRegisterationDate())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public MemberDto getMemberDtoBulider(Member member) {
		
		return MemberDto.builder()
				.memberId(member.getMemberId())
				.name(member.getName())
				.cardNo(member.getCardNo())
				.contactNo(member.getContactNo())
				.address(member.getAddress())
				.registerationDate(member.getRegisterationDate())
				.isActive(member.getIsActive())
				.build();
	}
	
	
	public MemberFundDto getMemberFundDtoBuilder(Member member,Double creditBalance,Double debitBalance) {
		Double balance=creditBalance - debitBalance; 
		return MemberFundDto.builder()
				.memberId(member.getMemberId())
				.name(member.getName())
				.cardNo(member.getCardNo())
				.balance(balance)
				.build();
	}

}
