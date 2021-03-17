package com.sunan.creditCustomerPayment;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.creditCustomer.CreditCustomerRepository;
import com.sunan.creditCustomerLedger.CreditCustomerLedgerDto;
import com.sunan.creditCustomerLedger.CreditCustomerLedgerRepository;
import com.sunan.model.CreditCustomer;
import com.sunan.model.CreditCustomerLedger;
import com.sunan.model.CreditCustomerPayment;
import com.sunan.utils.JsonUtils;

@Service
public class CreditCustomerPaymentService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreditCustomerPaymentService.class);

	@Autowired
	private CreditCustomerPaymentRepository creditCustomerPaymentRepository;

	@Autowired
	private CreditCustomerLedgerRepository creditCustomerLedgerRepository;

	@Autowired
	private CreditCustomerRepository creditCustomerRepository;

	@Autowired
	CreditCustomerPaymentMapper creditCustomerPaymentMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(CreditCustomerPaymentDto creditCustomerPaymentDto) {
		CreditCustomerPayment creditCustomerPayment = creditCustomerPaymentMapper
				.getCreditCustomerPaymentBuilder(creditCustomerPaymentDto);
		creditCustomerPaymentRepository.save(creditCustomerPayment);
		int creditCustomerPaymentId = creditCustomerPayment.getId();

		CreditCustomerLedgerDto creditCustomerLedgerDto = new CreditCustomerLedgerDto();
		creditCustomerLedgerDto.setDate(creditCustomerPaymentDto.getDate());
		creditCustomerLedgerDto
				.setLedgerNo("C " + creditCustomerPaymentId + " T " + creditCustomerPaymentDto.getCreditCustomerId());
		creditCustomerLedgerDto.setLabel("payment");
		creditCustomerLedgerDto.setDebit(0.0);
		creditCustomerLedgerDto.setCredit(creditCustomerPaymentDto.getAmount());
		creditCustomerLedgerDto.setIsActive("Active");
		creditCustomerLedgerDto.setCreditCustomerId(creditCustomerPaymentDto.getCreditCustomerId());

		CreditCustomerLedger creditCustomerLedger = creditCustomerPaymentMapper
				.getCreditCustomerLedgerBuilder(creditCustomerLedgerDto);
		creditCustomerLedgerRepository.save(creditCustomerLedger);

		logger.info("Service: credit customer payment details");
		return utils.objectMapperSuccess(
				creditCustomerPaymentMapper.getCreditCustomerPaymentDtoBuilder(creditCustomerPayment),
				" Credit customer payment Details Saved");
	}

	@Transactional
	public Object getCreditCustomerBalanceByCustomerId(int id) {
		logger.info("Service: Fetching  credit customer balance details with id {}", id);

		
	 List<CreditCustomerLedger> list = creditCustomerLedgerRepository.findCreditCustomerByCreditCustomer(new CreditCustomer(id));
		
		if (list != null) {
			logger.info("Service: credit customer details found with id {}", id);
			Optional<CreditCustomer> creditCustomer = creditCustomerRepository.findByCreditCustomerId(id);

			Double balance = creditCustomerLedgerRepository.getCreditCustomerBalanceByCreditCustomerId(new CreditCustomer(id));

			return utils.objectMapperSuccess(
					creditCustomerPaymentMapper.getCreditCustomerBalanceDtoBuilder(creditCustomer.get(), balance),
					"Credit Customer balance Details");
		} 
		else{
			logger.info("Service: credit customer balance details not found with id {}", id);
			return utils.objectMapperError("Credit customer balance Details Not found, Id :" + id);
		}

	}

}
