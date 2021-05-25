package com.sunan.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.Roles;

@Repository
public interface RolesRepository extends PagingAndSortingRepository<Roles, Integer> {
	public Optional<Roles> findById(int id);

	public Page<Roles> findByIsActive(String active, Pageable pageable);
	
	List<Roles> findByIsActive(String isActive);

	@Modifying
	@Query("UPDATE Roles SET isActive='no' WHERE id= :id ")
	int updateActiveStatus(int id);
}
