package com.sunan.supplier.payment;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.Supplier;
import com.sunan.model.SupplierLedger;
import com.sunan.supplier.SupplierRepository;
import com.sunan.supplier.ledger.SupplierLedgerRepository;
import com.sunan.utils.JsonUtils;

@Service
public class SupplierPaymentService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SupplierPaymentService.class);

	@Autowired
	private SupplierLedgerRepository supplierLedgerRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	SupplierPaymentMapper supplierPaymentMapper;

	
	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(SupplierPaymentDto supplierPaymentDto,int hotelId) {
		
		Optional<Supplier> supplier=supplierRepository.findById(supplierPaymentDto.getSupplierId());
		if(supplier.isPresent()) {
		Double transactionAmount = supplierPaymentDto.getAmount();
		Double supplierBalance = supplierLedgerRepository.getSupplierBalanceBySupplierId(new Supplier(supplierPaymentDto.getSupplierId()));
		
		if (transactionAmount < supplierBalance) {

			SupplierLedger supplierledger = supplierPaymentMapper.getSupplierPaymentBuilder(supplierPaymentDto);
			supplierledger.setHotel(new Hotel(hotelId));
			supplierLedgerRepository.save(supplierledger);

			logger.info("Service: supplier payment details");
			return utils.objectMapperSuccess(supplierPaymentMapper.getSupplierPaymentDtoBuilder(supplierledger),
					" Supplier payment Details Saved");
		} else {
			logger.info("Service: supplier balance is less than transaction amount");
			return utils.objectMapperError("Supplier balance is less than transaction amount");
		}
		}else {
			logger.info("Service: supplier not fount");
			return utils.objectMapperError("Supplier not found");
		}

	}

}
