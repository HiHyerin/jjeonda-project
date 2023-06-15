package com.fintech.jjeondaproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintech.jjeondaproject.dto.user.NaverResponseDto;
import com.fintech.jjeondaproject.dto.user.TokenResponseDto;
// name="feign client 이름 설정", url="호출할 api url", configuration="feignClient설정정보가 셋팅되어있는 클래스)
@FeignClient(name= "naver", url = "https://nid.naver.com/oauth2.0/")
public interface LoginFeign {
	
	// api를 호출할 메소드 세팅(인가코드받기) 3.4.4 접근 토큰 발급 요청
	@GetMapping(value = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	NaverResponseDto login(@RequestParam("grant_type") String grant_type,
							@RequestParam("client_id") String client_id,
							@RequestParam("client_secret") String client_secret,
							@RequestParam("code") String code,
							@RequestParam("state") String state);
	

}
