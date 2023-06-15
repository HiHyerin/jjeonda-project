package com.fintech.jjeondaproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
// 3.4.5 접근토큰을 이용하여 프로필 API 호출하기
@FeignClient(name= "naverProfile", url = "https://openapi.naver.com/v1/nid")
public interface ProfileFeign {
	@GetMapping(value="/me", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	ProfileResponseDto getProfile(@RequestHeader(value= "Authorization") String accessToken);

}
