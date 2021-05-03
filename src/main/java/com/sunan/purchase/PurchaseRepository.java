package com.sunan.purchase;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.Purchase;
import com.sunan.model.Supplier;

@Repository
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
	
	public Optional<Purchase> findById(int id);
	
	public Page<Purchase> findByIsActiveAndHotel(String active, Pageable pageable,Hotel hotel);
	
	@Query("SELECT p FROM Purchase p WHERE supplier_id= :supplier AND invoice_no= :invoiceNo AND payment_type = :paymentType AND hotel_id= :hotel AND date BETWEEN :fromDate AND :toDate")
	List<Purchase> getPurchaseReport(Date fromDate,Date toDate,Supplier supplier,String invoiceNo,String paymentType,Hotel hotel);

}
