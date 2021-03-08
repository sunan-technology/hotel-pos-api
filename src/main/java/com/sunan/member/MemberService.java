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

import com.sunan.model.Member;
import com.sunan.model.MemberLedger;
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
	private JsonUtils utils;

	@Autowired
	private MemberLedgerRepository memberLedgerRepository;

	@Autowired
	MemberLedgerMapper memberLedgerMapper;

	@Transactional
	public String save(MemberDto memberDto) {
		Member member = memberMapper.getMemberBuilder(memberDto);
		memberRepository.save(member);
		logger.info("Service: Save member details");
		return utils.objectMapperSuccess(memberMapper.getMemberDtoBulider(member), "Member Details Saved");
	}

	@Transactional
	public String update(MemberDto memberDto, int id) {
		logger.info("Service: Update member details with id {}", id);
		Optional<Member> optional = memberRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: member details found with id {} for update operation", id);
			Member member = memberMapper.getMemberBuilder(memberDto);
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
	public Object saveCreditAndDebitFunds(MemberLedgerDto dto) {

		MemberLedger memberLedger = memberLedgerMapper.getMemberLedgerBuilder(dto);
		memberLedgerRepository.save(memberLedger);

		logger.info("Service: Save member ledger details");
		return utils.objectMapperSuccess(memberLedgerMapper.getMemberLedgerDtoBuilder(memberLedger),
				"Member ledger Details Saved");
	}

	@Transactional
	public Object updateCreditAndDebitFunds(MemberLedgerDto dto, int id) {
		logger.info("Service: Update member credit and debit details with id {}", id);
		Optional<MemberLedger> optional = memberLedgerRepository.findById(id);

		if (optional.isPresent()) {
			logger.info("Service: member credit and debit details found with id {} for update operation", id);
			MemberLedger memberLedger = memberLedgerMapper.getMemberLedgerBuilder(dto);
			memberLedgerRepository.save(memberLedger);
			return utils.objectMapperSuccess(memberLedgerMapper.getMemberLedgerDtoBuilder(memberLedger),
					"Member credit and debit Details Updated");
		}
		logger.info("Service: member credit and debit details not found with id {} for update operation", id);
		return utils.objectMapperError("Member credit and debit Details Not Found !");

	}

	@Transactional
	public Object getMemberBalance(int id) {
		logger.info("Service: fetching member balance details with id {}", id);
		Optional<Member> optional = memberRepository.findById(id);

		if (optional.isPresent()) {
			logger.info("Service: memberdetails found with id {} ", id);
			Double creditBalance = memberLedgerRepository.sumofCreditBalanceOfMember();
			Double debitBalance = memberLedgerRepository.sumOfDebitBalanceOfMember();

			return utils.objectMapperSuccess(
					memberMapper.getMemberFundDtoBuilder(optional.get(), creditBalance, debitBalance),
					"Member balance details");
		} else {
			logger.info("Service: member details not found with id {}", id);
			return utils.objectMapperError("Member Details Not found, Id :" + id);
		}

	}

}
