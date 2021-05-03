package com.sunan.internal.transfer.join;

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
import com.sunan.model.InternalTransfer;
import com.sunan.model.InternalTransferJoin;
@Repository
public interface InternalTransferJoinRepository extends PagingAndSortingRepository<InternalTransferJoin, Integer> {
	
	List<InternalTransferJoin> findByInternalTransfer(InternalTransfer internalTransfer);
	
	public Optional<InternalTransferJoin> findById(int id);

	

	public Page<InternalTransferJoin> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);

	@Modifying
	@Query("UPDATE InternalTransferJoin SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);


}
