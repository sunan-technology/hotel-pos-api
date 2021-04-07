package com.sunan.table;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;

@Repository
public interface HotelTableRepository extends PagingAndSortingRepository<HotelTable, Integer> {

	public Optional<HotelTable> findByTableNo(int id);
	
	List<HotelTable> findByHotel(Hotel hotel);

	public Page<HotelTable> findByStatusContainingIgnoreCaseAndIsActive(String status, String active, Pageable pageable);

	public Page<HotelTable> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE HotelTable SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);
	


}
