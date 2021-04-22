package com.sunan.inventory.setting;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.InventorySetting;
@Repository
public interface InventorySettingRepository extends PagingAndSortingRepository<InventorySetting, Integer> {

}
