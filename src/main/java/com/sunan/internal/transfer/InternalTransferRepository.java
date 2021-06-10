package com.sunan.internal.transfer;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.InternalTransfer;
import com.sunan.model.Kitchen;
import com.sunan.model.Purchase;
@Repository
public interface InternalTransferRepository extends PagingAndSortingRepository<InternalTransfer, Integer> {

	public Page<InternalTransfer> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);
	
	@Query("SELECT i FROM InternalTransfer i WHERE kitchen_id= :kitchen  AND hotel_id= :hotel AND invoice_date BETWEEN :fromDate AND :toDate")
	List<InternalTransfer> getInternalTransferReport(Date fromDate,Date toDate,Kitchen kitchen,Hotel hotel);
	
	
	@Query("SELECT i FROM InternalTransfer i WHERE kitchen_id= :kitchen AND hotel_id= :hotel AND invoice_date BETWEEN :fromDate AND :toDate")
	List<InternalTransfer> getInternalTransferReportList(Date fromDate,Date toDate,Kitchen kitchen,Hotel hotel);
	
}
