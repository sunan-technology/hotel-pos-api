package com.sunan.billing.kot.info;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.BillingInfoKOT;
import com.sunan.model.Hotel;
@Repository
public interface BillingInfoKOTRepository extends PagingAndSortingRepository<BillingInfoKOT, Integer>,JpaSpecificationExecutor<BillingInfoKOT> {

	Page<BillingInfoKOT> findAll();


	@Modifying
	@Query( "SELECT SUM(grandtotal) FROM BillingInfoKOT b WHERE b.operator= :operator AND hotelId= :hotel")
	public Double getGrandTotalByOperatorAndHotel(@Param("operator") String operator,Hotel hotel);
	
	@Query("SELECT SUM(grandtotal) FROM BillingInfoKOT b WHERE b.paymentmode= :paymentMode")
	public Double getGrandTotalByPaymentMode(String paymentMode);
	
	@Query("SELECT SUM(grandtotal) FROM BillingInfoKOT b")
	public Double getGrandTotal();
	
	//public void deleteByHotelTableAndHotelId(HotelTable hotelTable, Hotel hotelId);
}
