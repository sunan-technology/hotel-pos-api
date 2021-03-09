package com.sunan.hotel;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer>
{
	public Optional<Hotel> findById(int id);
	
	
	public Page<Hotel>  findByHotelNameContainingIgnoreCaseAndIsActive(String hotelName,String active,Pageable pageable);
	
	public Page<Hotel> findByIsActive(String active, Pageable pageable);
	
	@Modifying
	@Query("UPDATE Hotel SET isActive='no' WHERE id= :id")
	int updateActiveStatus(@Param("id") int id);

}
