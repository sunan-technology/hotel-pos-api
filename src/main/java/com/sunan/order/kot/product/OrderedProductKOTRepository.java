package com.sunan.order.kot.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.OrderedProductKOT;
@Repository
public interface OrderedProductKOTRepository extends PagingAndSortingRepository<OrderedProductKOT, Integer> {

}
