package com.sunan.order.kot.temp.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderedProductKOT;
@Repository
public interface TempOrderedProductKOTRepository extends PagingAndSortingRepository<TempOrderedProductKOT, Integer> {

//	@Query("delete FROM  TempRestaurantPOSOrderedProductKOT WHERE table_id = :tableId")
	public void deleteByHotelTableAndHotel(HotelTable hotelTable, Hotel hotelId);
	
}

