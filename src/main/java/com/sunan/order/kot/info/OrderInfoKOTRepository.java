package com.sunan.order.kot.info;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.OrderInfoKOT;

@Repository
public interface OrderInfoKOTRepository extends PagingAndSortingRepository<OrderInfoKOT, Integer> {

}
