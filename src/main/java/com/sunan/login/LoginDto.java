package com.sunan.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	String userName;
	String password; 
}
