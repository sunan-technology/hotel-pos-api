package com.sunan.billing.kot.info;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.BillingInfoKOT;
@Repository
public interface BillingInfoKOTRepository extends PagingAndSortingRepository<BillingInfoKOT, Integer> {

	
	//public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
}
