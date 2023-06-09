package com.fintech.jjeondaproject.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.jjeondaproject.auth.Jwt;
import com.fintech.jjeondaproject.auth.JwtProvider;
import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.entity.UserEntity;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.util.Encryption;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
//	private final Encryption encryption;
	private final JwtProvider jwtProvider;
	
	public void join(UserDto userDto){
		System.out.println("userDto.getPassword():"+userDto.getPassword());
		String myEncryption = Encryption.encryptSHA512(userDto.getPassword());
		System.out.println("myEn:"+ myEncryption);
		UserEntity userEntity = UserEntity.builder()
				.accountId(userDto.getAccountId())
				.password(myEncryption)
				.name(userDto.getName())
				.phone(userDto.getPhone())
				.gender(userDto.getGender())
				.birth(userDto.getBirth())
				.email(userDto.getEmail())
				.regDate(userDto.getRegDate())
				.agreementYn(userDto.getAgreementYn())
				.build();
		userRepository.save(userEntity);
		
	}
	
	// id중복확인
	public boolean checkAccountId(String accountId) {
		return userRepository.existsByAccountId(accountId);
	}

	public String signIn(UserLoginDto userDto) {
		log.info("userDto={}",userDto.getPassword());
		UserEntity savedUser =  userRepository.findByAccountId(userDto.getAccountId());
		
		if(savedUser == null) {
			return "로그인 실패";
		}
//		log.info("Encryption.encryptSHA512(userDto.getPassword()):{}",encryption.encryptSHA512(userDto.getPassword()));
		if(Encryption.comparePwd(userDto.getPassword(), savedUser.getPassword())) { // input pwd와 db pwd가 같다면..
			log.info("@@@@@@@@@@@@@@@savedUser.getAccountId()={}",savedUser.getAccountId());
			Map<String, Object> claims = new HashMap<>();

			/**
			 * 이 부분 UserId -> UserAccountId 로 변경됨으로 인해 Error 발생할 수 있음.
			 * 확인 바람
			 */
			claims.put("UserAccountId", savedUser.getAccountId()); // claims 내용 추가1
			claims.put("UserName", savedUser.getName()); // claims 내용 추가2
			claims.put("UserId", savedUser.getId()); // claims 내용 추가3(userPk값)
			Jwt jwt = jwtProvider.createJwt(claims); // JWT 생성
			savedUser.updateRefreshToken(jwt.getRefreshToken()); // 발급된 refreshToken을 user에게 주입
			userRepository.save(savedUser); // refreshToken db에 저장
			
			return jwt.getAccessToken();
		}
		return "로그인 실패";
	}

	public ProfileResponseDto getRequireUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
