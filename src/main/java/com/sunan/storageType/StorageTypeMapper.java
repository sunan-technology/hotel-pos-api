package com.sunan.storageType;

import org.springframework.stereotype.Component;

import com.sunan.model.StorageType;

@Component
public class StorageTypeMapper {
	
	
	public StorageType getStorageTypeBuilder(StorageTypeDto dto) {
		
		return StorageType.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public StorageTypeDto getStorageTypeDtoBuilder(StorageType storageType)
	{
		return StorageTypeDto.builder()
				.id(storageType.getId())
				.name(storageType.getName())
				.isActive(storageType.getIsActive())
				.build();
	}

}
