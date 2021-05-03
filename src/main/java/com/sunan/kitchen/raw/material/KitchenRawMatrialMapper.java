package com.sunan.kitchen.raw.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.internal.transfer.join.InternalTransferJoinDto;
import com.sunan.model.Hotel;
import com.sunan.model.Kitchen;
import com.sunan.model.KitchenRawMatrial;
import com.sunan.model.Units;

@Component
public class KitchenRawMatrialMapper {
	
	public KitchenRawMatrial getKitchenRawMatrialBuilder(KitchenRawMatrialDto dto) {
		
		return KitchenRawMatrial.builder()
				.id(dto.getId())
				.rawMatrialName(dto.getRawMatrialName())
				.quantity(dto.getQuantity())
				.transferDate(new Date())
				.units(new Units(dto.getUnitsId()))
				.kitchen(new Kitchen(dto.getKitchenId()))
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public List<KitchenRawMatrial> getKitchenRawMatrial(List<InternalTransferJoinDto> internalTransferJoinDto,int hotelId,int kitchenId){
		
		List<KitchenRawMatrial> list=new ArrayList<KitchenRawMatrial>();
		for(InternalTransferJoinDto dto : internalTransferJoinDto ) {
			
			list.add(KitchenRawMatrial.builder()
					.id(dto.getId())
					.rawMatrialName(dto.getRawMatrialName())
					.quantity(dto.getQuantity())
					.transferDate(new Date())
					.units(new Units(dto.getUnitsId()))
					.kitchen(new Kitchen(kitchenId))
					.hotel(new Hotel(hotelId))
					.isActive(dto.getIsActive())
					.build());
		}
		
		return list;
		
	}

}
