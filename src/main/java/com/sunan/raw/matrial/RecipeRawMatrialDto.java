package com.sunan.raw.matrial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRawMatrialDto {

	private String name;
	private int quantity;
	private int unitId;
	private String area;

}
