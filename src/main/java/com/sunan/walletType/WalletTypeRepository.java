package com.sunan.walletType;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.WalletType;

@Repository
public interface WalletTypeRepository extends PagingAndSortingRepository<WalletType, Integer> {

	public Optional<WalletType> findById(int id);

	Page<WalletType> findByNameContainingIgnoreCaseAndIsActive(String name, String active, Pageable pageable);

	Page<WalletType> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE WalletType SET isActive='no' WHERE id= :id")
	int updateActiveStatus(@Param("id") int id);

}
