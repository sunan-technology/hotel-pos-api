package com.sunan.unit;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.Units;

@Repository
public interface UnitsRepository extends PagingAndSortingRepository<Units, Integer> {

	public Optional<Units> findById(int id);

	public Page<Units> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);

	@Modifying
	@Query("UPDATE Units SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);

}
