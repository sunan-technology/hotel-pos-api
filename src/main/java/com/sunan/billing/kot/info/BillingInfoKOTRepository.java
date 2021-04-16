package com.sunan.billing.kot.info;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.BillingInfoKOT;
import com.sunan.model.Hotel;
@Repository
public interface BillingInfoKOTRepository extends PagingAndSortingRepository<BillingInfoKOT, Integer>,JpaSpecificationExecutor<BillingInfoKOT> {

	Page<BillingInfoKOT> findAll();



	@Query( "SELECT SUM(grandTotal) FROM BillingInfoKOT b WHERE b.operator= :operator AND hotel_id= :hotel AND billdate BETWEEN :fromDate AND :toDate")
	public Double sumGrandTotalByOperatorAndHotel( String operator,Hotel hotel,Date fromDate,Date toDate);
	
	@Query("SELECT SUM(grandTotal) FROM BillingInfoKOT WHERE paymentMode= :paymentMode  AND billdate BETWEEN :fromDate AND :toDate")
	public Double sumGrandTotalByPaymentMode(String paymentMode,Date fromDate,Date toDate);
	
	@Query("SELECT SUM(grandTotal) FROM BillingInfoKOT WHERE hotel_id= :hotel  AND billdate BETWEEN :fromDate AND :toDate")
	public Double sumGrandTotalByHotel(Hotel hotel,Date fromDate,Date toDate);
	
	@Query("SELECT FROM BillingInfoKOT WHERE billdate BETWEEN :fromDate AND :toDate")
	public List<BillingInfoKOT> findByBillDate(Date fromDate,Date toDate);
	
	//public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
}
