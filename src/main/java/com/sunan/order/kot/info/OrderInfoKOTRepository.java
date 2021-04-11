package com.sunan.order.kot.info;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.OrderInfoKOT;

@Repository
public interface OrderInfoKOTRepository extends PagingAndSortingRepository<OrderInfoKOT, Integer> {

	
	@Modifying
	@Query("UPDATE OrderInfoKOT SET grandTotal = :grandTotal WHERE id = :ticketId AND hotel_Id= :hotel")
	void updateGrandTotalByHotelAndTicketId(Hotel hotel,int ticketId, Double grandTotal);
	
	
	@Modifying
	@Query("UPDATE OrderInfoKOT  SET table_id = :tableId WHERE  hotel_id = :hotel AND id = :ticketId")
	int changeTable(Hotel hotel,int ticketId,int tableId);
	
}
