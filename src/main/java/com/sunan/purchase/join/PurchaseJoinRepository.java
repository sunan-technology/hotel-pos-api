package com.sunan.purchase.join;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.Purchase;
import com.sunan.model.RawMatrial;
import com.sunan.model.Warehouses;

@Repository
public interface PurchaseJoinRepository extends PagingAndSortingRepository<PerchaseJoin, Integer>,JpaSpecificationExecutor<PerchaseJoin> {

	@Modifying
	@Query("UPDATE PerchaseJoin  SET quantity = :quantity ,total_amount = :totalAmount WHERE  hotel_id = :hotel AND id = :purchasejoinId")
	void updateQuantity(Hotel hotel, int quantity, int purchasejoinId, Double totalAmount);

	List<PerchaseJoin> findByPurchase(Purchase purchase);

	public Page<PerchaseJoin> findByIsActiveAndHotel(String active, Pageable pageable, Hotel hotel);

	@Query("SELECT SUM(quantity) FROM PerchaseJoin WHERE hotel_id= :hotel  AND rawmatrial_name = :rawMatrialName")
	public int sumQuantityByHotelAndRawMatrialName(Hotel hotel, String rawMatrialName);
	
	@Query("SELECT new com.sunan.purchase.join.AvailableRawMatrialDto( SUM(quantity) ,rawMatrialName,rawMatrial.id ) FROM PerchaseJoin  WHERE   hotel_id= :hotel AND warehouses_id= :warehouses GROUP BY rawMatrialName, rawmatrial_id ORDER BY SUM(quantity) desc")
	List<AvailableRawMatrialDto> getAvailableRawMatrial(Hotel hotel, Warehouses warehouses);
	
	@Query("SELECT p FROM PerchaseJoin p WHERE quantity > 0 AND rawmatrial_id = :rawmatrial AND hotel_id= :hotel")
	List<PerchaseJoin> getRawmatrialList(RawMatrial rawmatrial,Hotel hotel);
	
	

}
