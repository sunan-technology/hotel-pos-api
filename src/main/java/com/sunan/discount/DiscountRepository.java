package com.sunan.discount;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Discount;
import com.sunan.model.Hotel;
@Repository
public interface DiscountRepository extends PagingAndSortingRepository<Discount, Integer> {

	public Optional<Discount> findById(int id);


	public Page<Discount> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);

	@Modifying
	@Query("UPDATE Discount SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);
	
}
