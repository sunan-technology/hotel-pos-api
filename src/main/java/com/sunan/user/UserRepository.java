package com.sunan.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.User;
import com.sunan.model.Hotel;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	public Optional<User> findById(int id);

	// public Page<Customer>
	// findByCategoryNameContainingIgnoreCaseAndIsActive(String categoryName,String
	// active,Pageable pageable);

	public Page<User> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE User SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);

	
	boolean existsByIdAndHotel(int employeeId,Hotel hotel);
	
	Optional<User> findByUserName(String username);

}
