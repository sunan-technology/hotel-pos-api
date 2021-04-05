package com.sunan.TempRestaurantPOS_OrderedProductKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempRestaurantPOSOrderedProductKOT;
@Repository
public interface TempRestaurantPOSOrderedProductKOTRepository extends PagingAndSortingRepository<TempRestaurantPOSOrderedProductKOT, Integer> {

//	@Query("delete FROM  TempRestaurantPOSOrderedProductKOT WHERE table_id = :tableId")
	public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
	
}

