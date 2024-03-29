package com.sunan.member;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.hotel.HotelRepository;
import com.sunan.member.ledger.MemberLedgerDto;
import com.sunan.member.ledger.MemberLedgerMapper;
import com.sunan.member.ledger.MemberLedgerRepository;
import com.sunan.model.Hotel;
import com.sunan.model.Member;
import com.sunan.model.MemberLedger;
import com.sunan.utils.EmailUtil;
import com.sunan.utils.JsonUtils;

@Service
public class MemberService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private JsonUtils utils;
	
	@Autowired
	EmailUtil emailUtil;

	@Autowired
	private MemberLedgerRepository memberLedgerRepository;

	@Autowired
	MemberLedgerMapper memberLedgerMapper;

	@Transactional
	public String save(MemberDto memberDto, int hotelId) {
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Member member = memberMapper.getMemberBuilder(memberDto);
			member.setHotel(new Hotel(hotelId));
			memberRepository.save(member);
			emailUtil.sendEmail(member.getEmail(), emailUtil.getEmailMsg());
			logger.info("Service: Save member details");
			return utils.objectMapperSuccess(memberMapper.getMemberDtoBulider(member), "Member Details Saved");
		} else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}
	}

	@Transactional
	public String update(MemberDto memberDto, int id, int hotelId) {
		logger.info("Service: Update member details with id {}", id);
		Optional<Member> optional = memberRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: member details found with id {} for update operation", id);

			Member member = memberMapper.getMemberBuilder(memberDto);
			member.setHotel(new Hotel(hotelId));
			memberRepository.save(member);
			return utils.objectMapperSuccess(memberMapper.getMemberDtoBulider(member), "Member Details Updated");
		}
		logger.info("Service: member details not found with id {} for update operation", id);
		return utils.objectMapperError("Member Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete member details with id {}", id);
		int isDelete = memberRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: member details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Member Deleted Successfully");
		}
		logger.info("Service: member details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Member Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching member details with id {}", id);
		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			logger.info("Service: member details found with id {}", id);
			MemberDto dto = memberMapper.getMemberDtoBulider(member.get());
			return utils.objectMapperSuccess(dto, "Member Details");
		}
		logger.info("Service: member details not found with id {}", id);
		return utils.objectMapperError("Member Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of kitchen details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Member> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = memberRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = memberRepository.findByNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<MemberDto> page = pagedResult.map(new Function<Member, MemberDto>() {
			@Override
			public MemberDto apply(Member entity) {
				MemberDto dto = memberMapper.getMemberDtoBulider(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of member details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive member list.");
	}

	@Transactional
	public Object saveFunds(String fundType) {

		if (fundType.equalsIgnoreCase("credit")) {

		} else {

		}
		return null;
	}

	@Transactional
	public Object saveCreditAndDebitFunds(MemberLedgerDto dto, int hotelId) {
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<Member> member = memberRepository.findById(dto.getMemberId());
			if (member.isPresent()) {
				MemberLedger memberLedger = memberLedgerMapper.getMemberLedgerBuilder(dto);
				memberLedger.setHotel(new Hotel(hotelId));
				memberLedgerRepository.save(memberLedger);

				logger.info("Service: Save member ledger details");
				return utils.objectMapperSuccess(memberLedgerMapper.getMemberLedgerDtoBuilder(memberLedger),
						"Member ledger Details Saved");
			} else {
				logger.info("Service : member not present");
				return utils.objectMapperError("Member not present");
			}
		} else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}
	}

	@Transactional
	public Object updateCreditAndDebitFunds(MemberLedgerDto dto, int id, int hotelId) {
		logger.info("Service: Update member credit and debit details with id {}", id);
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<MemberLedger> optional = memberLedgerRepository.findById(id);

			if (optional.isPresent()) {
				Optional<Member> member = memberRepository.findById(dto.getMemberId());
				if (member.isPresent()) {
					logger.info("Service: member credit and debit details found with id {} for update operation", id);
					MemberLedger memberLedger = memberLedgerMapper.getMemberLedgerBuilder(dto);
					memberLedger.setHotel(new Hotel(hotelId));
					memberLedgerRepository.save(memberLedger);
					return utils.objectMapperSuccess(memberLedgerMapper.getMemberLedgerDtoBuilder(memberLedger),
							"Member credit and debit Details Updated");
				} else {
					logger.info("Service : member not present");
					return utils.objectMapperError("Member not present");
				}
			}
			logger.info("Service: member credit and debit details not found with id {} for update operation", id);
			return utils.objectMapperError("Member credit and debit Details Not Found !");
		} else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}

	}

	@Transactional
	public Object getMemberBalance(int id, int hotelId) {
		logger.info("Service: fetching member balance details with id {}", id);
		Optional<Member> optional = memberRepository.findById(id);

		if (optional.isPresent()) {
			logger.info("Service: memberdetails found with id {} ", id);
			Double creditBalance = memberLedgerRepository.sumofCreditBalanceOfMember(new Hotel(hotelId),
					new Member(id));
			Double debitBalance = memberLedgerRepository.sumOfDebitBalanceOfMember(new Hotel(hotelId), new Member(id));

			return utils.objectMapperSuccess(
					memberMapper.getMemberFundDtoBuilder(optional.get(), creditBalance, debitBalance),
					"Member balance details");
		} else {
			logger.info("Service: member details not found with id {}", id);
			return utils.objectMapperError("Member Details Not found, Id :" + id);
		}

	}

}
