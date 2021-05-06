package com.sunan.delivery.distance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.DeliveryDistance;
import com.sunan.model.Hotel;

@Repository
public interface DeliveryDistanceRepository extends PagingAndSortingRepository<DeliveryDistance, Integer> {

	public Optional<DeliveryDistance> findById(int id);

	public Page<DeliveryDistance> findByIsActiveAndHotel(String active, Pageable pageable, Hotel hotel);

	@Modifying
	@Query("UPDATE DeliveryDistance SET isActive='no' WHERE id= :id ")
	int updateActiveStatus(int id);
}
