package com.sunan.order.kot.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.OrderInfoKOT;
import com.sunan.model.OrderedProductKOT;
@Repository
public interface OrderedProductKOTRepository extends PagingAndSortingRepository<OrderedProductKOT, Integer> {

	
	@Modifying
	@Query("UPDATE OrderedProductKOT SET quantity = :quantity WHERE id = :dishId AND hotel_id = :hotel")
	void updateQuantityByHotelAndTicketId(Hotel hotel,int dishId,int quantity);
	
	@Modifying
	@Query("UPDATE OrderedProductKOT  SET table_id = :tableId WHERE  hotel_id = :hotel AND id = :ticketId")
	int changeTable(Hotel hotel,int ticketId,int tableId);
	
	
	public Optional<OrderedProductKOT>findByIdAndOrderInfoKOT(int dishId,OrderInfoKOT ticketId);
}
