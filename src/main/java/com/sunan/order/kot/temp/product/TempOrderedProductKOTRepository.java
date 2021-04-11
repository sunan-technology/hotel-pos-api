package com.sunan.order.kot.temp.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.TempOrderedProductKOT;
@Repository
public interface TempOrderedProductKOTRepository extends PagingAndSortingRepository<TempOrderedProductKOT, Integer> {


	public void deleteByHotelTableAndHotel(HotelTable hotelTable, Hotel hotelId);
	
	@Modifying
	@Query("UPDATE TempOrderedProductKOT SET quantity = :quantity WHERE id = :dishId AND hotel_id = :hotel")
	int updateQuantityByHotelAndTicketId(Hotel hotel,int dishId,int quantity);
	
	@Modifying
	@Query("UPDATE TempOrderedProductKOT  SET table_id = :tableId WHERE  hotel_id = :hotel AND ticketid = :ticketId")
	int changeTable(Hotel hotel,TempOrderInfoKOT ticketId,int tableId);
	
	public Optional<TempOrderedProductKOT>findByIdAndTempOrderInfoKOT(int dishId,TempOrderInfoKOT ticketId);
	
	public void deleteByTempOrderInfoKOTAndId(TempOrderInfoKOT ticketId,int dishId);
	
	public Optional<TempOrderedProductKOT> findByHotelTableAndHotel(HotelTable tableNo, Hotel hotelId);
	
	public List<TempOrderedProductKOT> findByTempOrderInfoKOT(TempOrderInfoKOT ticketId);
	
	public void deleteByTempOrderInfoKOT(TempOrderInfoKOT ticketId);
	
}

