package com.sunan.RestaurantPOS_OrderedProductKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.RestaurantPOSOrderedProductKOT;
@Repository
public interface RestaurantPOSOrderedProductKOTRepository extends PagingAndSortingRepository<RestaurantPOSOrderedProductKOT, Integer> {

}
