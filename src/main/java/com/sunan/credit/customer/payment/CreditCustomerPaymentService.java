package com.sunan.credit.customer.payment;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.credit.customer.CreditCustomerRepository;
import com.sunan.credit.customer.ledger.CreditCustomerLedgerMapper;
import com.sunan.credit.customer.ledger.CreditCustomerLedgerRepository;
import com.sunan.model.CreditCustomer;
import com.sunan.model.CreditCustomerLedger;
import com.sunan.model.CreditCustomerPayment;
import com.sunan.model.Hotel;
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
	CreditCustomerLedgerMapper creditCustomerLedgerMapper;
	
	@Autowired
	private JsonUtils utils;
	
	 

	@Transactional
	public String save(CreditCustomerPaymentDto creditCustomerPaymentDto,int hotelId) {
		
		Optional<CreditCustomer> optional = creditCustomerRepository.findById(creditCustomerPaymentDto.getCreditCustomerId());
		if(!optional.isPresent()) {
			return utils.objectMapperError("Category not found. Pass valid category id in the request");
		} 
		
		CreditCustomerPayment creditCustomerPayment = creditCustomerPaymentMapper
				.getCreditCustomerPaymentBuilder(creditCustomerPaymentDto);
		creditCustomerPayment.setHotel(new Hotel(hotelId));
		creditCustomerPaymentRepository.save(creditCustomerPayment); 

		CreditCustomerLedger creditCustomerLedger = creditCustomerLedgerMapper.getCreditCustomerLedgerBuilder(creditCustomerPaymentDto, creditCustomerPayment.getId());
		creditCustomerLedger.setHotel(new Hotel(hotelId));
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
