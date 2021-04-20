package com.sunan.raw.matrial;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.RawMatrial;

@Repository
public interface RawMatrialRepository extends PagingAndSortingRepository<RawMatrial, Integer> {

	public Optional<RawMatrial> findById(int id);

	public Page<RawMatrial> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);

	@Modifying
	@Query("UPDATE RawMatrial SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);

}
