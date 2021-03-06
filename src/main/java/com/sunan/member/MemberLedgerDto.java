package com.sunan.member;

import java.util.Date;

import com.sunan.model.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLedgerDto {

	private int ledgerNo;

	private Date date;

	private String lable;

	private Double credit;

	private Double debit;

	private int memberId;

	private String name;

}
