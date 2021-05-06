package com.sunan.sub.order.type;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.SubOrderType;

@Repository
public interface SubOrderTypeRepository extends PagingAndSortingRepository<SubOrderType, Integer> {

	public Optional<SubOrderType> findById(int id);

	public Page<SubOrderType> findByIsActiveAndHotel(String active, Pageable pageable, Hotel hotel);

	@Modifying
	@Query("UPDATE SubOrderType SET isActive='no' WHERE id= :id ")
	int updateActiveStatus(int id);

}
