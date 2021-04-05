package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempRestaurantPOSOrderInfoKOT;

@Repository
public interface TempRestaurantPOSOrderInfoKOTRepository extends PagingAndSortingRepository<TempRestaurantPOSOrderInfoKOT, Integer> {

	@Query("delete FROM TempRestaurantPOSOrderInfoKOT t WHERE table_id = :tableId ")
	public void deleteByTableId(int tableId);
	
	public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
	
	
}
