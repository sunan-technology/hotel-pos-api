package com.sunan.utils;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunan.model.User;
import com.sunan.user.UserRepository;

@Component
public class PasswordUtil {
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";

	@Autowired
	UserRepository userRepository;

	public String getUserName(int len) {
		String userName = "";
		boolean isUserName = true;
		do {
			int length = 6;
			if (len < 5 || len > 6)
				length = 6;
			else
				length = len;
			userName = RandomStringUtils.random(length, true, true);
			Optional<User> optUser = userRepository.findByUserName(userName);
			if (!optUser.isPresent()) {
				isUserName = false;
			}

		} while (isUserName);
		return userName;
	}

	public static String getRandomNumberString() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}

	public static void gene() {
		int length = 6;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		System.out.println(generatedString);
	}

	public static void main(String[] args) {
		int len = 8;
//		gene();
//		System.out.println(new PasswordUtil().getUserName(len));
//		System.out.println(getRandomNumberString());
//		String originalString = "password";
//		System.out.println("Original String to encrypt - " + originalString);
//		String encryptedString = encrypt("123");
//		System.out.println("Encrypted String - " + encryptedString);
		String decryptedString = decrypt("1V/HEhSFe8gsmlW5LOBqjQ==");
		System.out.println("After decryption - " + decryptedString);
	}

	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
