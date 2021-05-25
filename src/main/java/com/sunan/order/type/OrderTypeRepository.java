package com.sunan.order.type;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.OrderType;
@Repository
public interface OrderTypeRepository extends PagingAndSortingRepository<OrderType, Integer> {
	
	public Optional<OrderType> findById(int id);


	public Page<OrderType> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);

	@Modifying
	@Query("UPDATE OrderType SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);
	

}
