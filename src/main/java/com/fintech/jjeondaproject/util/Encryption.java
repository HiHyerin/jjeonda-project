package com.fintech.jjeondaproject.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;

import org.springframework.stereotype.Component;
@Component
public class Encryption {
	static String configPath = ".\\src\\main\\resources\\config.yml"; 
	static Properties config = new MyConfigReader().readConfig(configPath);
	private static String SALT = config.getProperty("salt");
	public static String encryptSHA512(String password){
		try {
			if(password != null && SALT != null) {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				md.reset();
				
				md.update(SALT.getBytes());
				
				byte[] digested = md.digest(password.getBytes());
				
				return String.format("%0128x", new BigInteger(1, digested));
			} else {
				return "";
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static boolean comparePwd(String pwd, String dbPwd) {
		
		return encryptSHA512(pwd).equals(dbPwd);
	}
	
	public static void main(String[] args) {
		String pwd = "1111";
		String dbPwd = encryptSHA512(pwd);
		System.out.println(dbPwd);
		System.out.println(comparePwd(pwd, dbPwd));
		
		String pwd2 = "1112";
		String dbPwd2 = encryptSHA512(pwd2);
		System.out.println(comparePwd(pwd2, dbPwd2));
		System.out.println(dbPwd2);
		
	}
}
