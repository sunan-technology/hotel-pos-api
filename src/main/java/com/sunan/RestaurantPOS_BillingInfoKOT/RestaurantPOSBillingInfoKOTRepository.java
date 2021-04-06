package com.sunan.RestaurantPOS_BillingInfoKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.RestaurantPOSBillingInfoKOT;
@Repository
public interface RestaurantPOSBillingInfoKOTRepository extends PagingAndSortingRepository<RestaurantPOSBillingInfoKOT, Integer> {

	
	//public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
}
