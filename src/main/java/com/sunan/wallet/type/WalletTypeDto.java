package com.sunan.wallet.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletTypeDto {

	private int id;
	private String name;
	private String isActive;

}
