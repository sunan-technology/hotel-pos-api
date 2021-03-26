package com.sunan.TempRestaurantPOS_OrderedProductKOT;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.TempRestaurantPOSOrderedProductKOT;
@Repository
public interface TempRestaurantPOSOrderedProductKOTRepository extends PagingAndSortingRepository<TempRestaurantPOSOrderedProductKOT, Integer> {

}
