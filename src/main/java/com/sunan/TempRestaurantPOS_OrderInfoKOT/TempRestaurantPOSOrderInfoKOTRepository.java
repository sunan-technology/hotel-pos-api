package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempRestaurantPOSOrderInfoKOT;

@Repository
public interface TempRestaurantPOSOrderInfoKOTRepository extends PagingAndSortingRepository<TempRestaurantPOSOrderInfoKOT, Integer> {

	
	public void deleteByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);
	
	
}
