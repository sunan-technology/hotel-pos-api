package com.sunan.walletType;

import org.springframework.stereotype.Component;

import com.sunan.model.WalletType;

@Component
public class WalletTypeMapper {
	
	public WalletType getWalletTypeBuilder(WalletTypeDto dto) {
		
		return WalletType.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isActive(dto.getIsActive())
				.build();
		
	}
	
	
	public WalletTypeDto getWalletTypeDtoBuilder(WalletType walletType) {
		
		return WalletTypeDto.builder()
				.id(walletType.getId())
				.name(walletType.getName())
				.isActive(walletType.getIsActive())
				.build();
	}

}
