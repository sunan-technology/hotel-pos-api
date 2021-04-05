package com.sunan.RestaurantPOS_OrderedProductBillKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.RestaurantPOSOrderedProductBillKOT;

@Repository
public interface RestaurantPOSOrderedProductBillKOTRepository extends PagingAndSortingRepository<RestaurantPOSOrderedProductBillKOT, Integer> {

}
