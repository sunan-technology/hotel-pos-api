package com.sunan.discount;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.DiscountOrderType;
@Repository
public interface DiscountOrderTypeRepository extends PagingAndSortingRepository<DiscountOrderType, Integer> {
	
	@Modifying
	@Query("UPDATE DiscountOrderType SET isActive='no' WHERE discount_id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
