package com.sunan.order.kot.temp.info;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;

@Repository
public interface TempOrderInfoKOTRepository extends PagingAndSortingRepository<TempOrderInfoKOT, Integer> {

	
	public void deleteByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);
	
	public Optional<TempOrderInfoKOT> findByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);
}
