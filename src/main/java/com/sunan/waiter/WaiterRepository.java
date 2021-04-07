package com.sunan.waiter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Waiter;
@Repository
public interface WaiterRepository extends PagingAndSortingRepository<Waiter, Integer> {
	
public Optional<Waiter> findById(int id);
	
	
	
	public Page<Waiter> findByIsActive(String active,Pageable pageable);
	
	@Modifying
	@Query("UPDATE Waiter SET isActive='no' WHERE id= :id ")
	int updateActiveStatus(int id);

}
