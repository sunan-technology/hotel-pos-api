package com.sunan.order.type;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.OrderType;
@Repository
public interface OrderTypeRepository extends PagingAndSortingRepository<OrderType, Integer> {

}
