package com.sunan.member.ledger;

import org.springframework.stereotype.Component;

import com.sunan.model.Member;
import com.sunan.model.MemberLedger;

@Component
public class MemberLedgerMapper {
	
	public MemberLedger getMemberLedgerBuilder(MemberLedgerDto dto) {
		
		return MemberLedger.builder()
				.ledgerNo(dto.getLedgerNo())
				.date(dto.getDate())
				.lable(dto.getLable())
				.credit(dto.getCredit())
				.debit(dto.getDebit())
				.member(new Member(dto.getMemberId()))
				.build();
				

	}
	
	
	public MemberLedgerDto getMemberLedgerDtoBuilder(MemberLedger memberLedger)
	{
		return MemberLedgerDto.builder()
				.ledgerNo(memberLedger.getLedgerNo())
				.date(memberLedger.getDate())
				.lable(memberLedger.getLable())
				.credit(memberLedger.getCredit())
				.debit(memberLedger.getDebit())
				.memberId(memberLedger.getMember().getMemberId())
				.name(memberLedger.getMember().getName())
				.build();
				
	}
	

}
