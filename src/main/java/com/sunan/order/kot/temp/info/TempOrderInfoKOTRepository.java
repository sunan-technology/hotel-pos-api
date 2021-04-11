package com.sunan.order.kot.temp.info;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;

@Repository
public interface TempOrderInfoKOTRepository extends PagingAndSortingRepository<TempOrderInfoKOT, Integer> {

	public void deleteByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);

	public TempOrderInfoKOT findByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);

	@Modifying
	@Query("UPDATE TempOrderInfoKOT  SET grandTotal = :grandTotal WHERE  hotel_id = :hotel AND id = :ticketId")
	void updateGrandTotalByHotelAndTicketId(Hotel hotel, int ticketId, Double grandTotal);

	@Modifying
	@Query("UPDATE TempOrderInfoKOT  SET table_id = :tableId WHERE  hotel_id = :hotel AND id = :ticketId")
	int changeTable(Hotel hotel, int ticketId, int tableId);

}
