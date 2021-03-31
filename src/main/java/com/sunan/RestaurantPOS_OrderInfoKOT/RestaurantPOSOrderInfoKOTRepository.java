package com.sunan.RestaurantPOS_OrderInfoKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.RestaurantPOSOrderInfoKOT;

@Repository
public interface RestaurantPOSOrderInfoKOTRepository extends PagingAndSortingRepository<RestaurantPOSOrderInfoKOT, Integer> {

}
