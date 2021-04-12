package com.sunan.pos.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sunan.billing.kot.info.BillingInfoKOTRepository;
import com.sunan.model.BillingInfoKOT;
import com.sunan.model.BillingInfoKOT_;
import com.sunan.model.Hotel;
import com.sunan.utils.Common;
import com.sunan.utils.JsonUtils;

@Service
public class POSReportService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(POSReportService.class);
	
	@Autowired
	private BillingInfoKOTRepository billingInfoKotRepository;
	
	@Autowired
	private JsonUtils utils;
	
	
	@Transactional
	public String overAllReport(Integer pageNo, Integer pageSize, String sortBy, int hotelId, Date fromDate, Date toDate,String operator) {
		
		logger.info("Getting data for over all report..");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Specification<BillingInfoKOT> specification = new Specification<BillingInfoKOT>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();
			@Override
			public Predicate toPredicate(Root<BillingInfoKOT> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.equal(root.get(BillingInfoKOT_.IS_ACTIVE), Common.isActiveFlagYes);
				predicates.add(predicate);
				if (fromDate != null & toDate != null) {
					Predicate p2 = criteriaBuilder.between(root.get(BillingInfoKOT_.BILL_DATE), fromDate, toDate);
					predicates.add(p2);
				}
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})))
				.orderBy(criteriaBuilder.desc(root.get("hotelId")));
				return query.getRestriction();
			}	
		};
		
		Page<BillingInfoKOT> pagedResult = billingInfoKotRepository.findAll(specification, pageable);
		Page<OverAllReportDto> page=pagedResult.map(new Function<BillingInfoKOT, OverAllReportDto>() {

			@Override
			public OverAllReportDto apply(BillingInfoKOT entity) {
				OverAllReportDto dto=new OverAllReportDto();
				dto.setHotelName(entity.getHotel().getHotelName());
				dto.setHotelAddress(entity.getHotel().getAddress1());
				dto.setContactNo(entity.getHotel().getContactNo());
				dto.setEmail(entity.getHotel().getEmail());
				dto.setFromDate(fromDate);
				dto.setToDate(toDate);
				dto.setSaleByOprator(billingInfoKotRepository.getGrandTotalByOperatorAndHotel(operator,new Hotel(hotelId)));
				dto.setCash(billingInfoKotRepository.getGrandTotalByPaymentMode("cash"));
				dto.setCard(billingInfoKotRepository.getGrandTotalByPaymentMode("card"));
				dto.setWallet(billingInfoKotRepository.getGrandTotalByPaymentMode("wallet"));
				dto.setDineIn(billingInfoKotRepository.getGrandTotal());
				return dto;
			}
		});
		return utils.objectMapperSuccess(page, "Over All billing report list.");
	}

}
