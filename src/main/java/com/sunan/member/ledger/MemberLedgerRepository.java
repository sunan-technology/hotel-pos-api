package com.sunan.member.ledger;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.Member;
import com.sunan.model.MemberLedger;


@Repository
public interface MemberLedgerRepository extends PagingAndSortingRepository<MemberLedger, Integer> {
	
	public Optional<MemberLedger> findByLedgerNo(int id);
	
	@Query("SELECT SUM(b.credit-b.debit) FROM MemberLedger b")
	public double getMemberBalance();
	
	
	@Query(value = "SELECT SUM(credit) FROM MemberLedger WHERE hotel_id = :hotel AND member_id= :member")
	public Double sumofCreditBalanceOfMember(Hotel hotel,Member member);
	
	@Query(value = "SELECT SUM(debit) FROM MemberLedger  WHERE hotel_id = :hotel AND member_id= :member")
	public Double sumOfDebitBalanceOfMember(Hotel hotel,Member member);
	
	
	
	

}
