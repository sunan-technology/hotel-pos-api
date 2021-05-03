package com.sunan.kitchen.raw.material;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.KitchenRawMatrial;

@Repository
public interface KitchenRawMatrialRepository extends PagingAndSortingRepository<KitchenRawMatrial, Integer> {

}
