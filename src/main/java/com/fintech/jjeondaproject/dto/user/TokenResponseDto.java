package com.fintech.jjeondaproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
	String access_token;
	String refresh_token;
	String token_type;
	Integer expires_in;
}
