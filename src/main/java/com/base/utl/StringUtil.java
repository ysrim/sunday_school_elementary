package com.base.utl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StringUtil {

	private StringUtil() {
	}

	public static String comma(int number) {
		String formattedNumber = NumberFormat.getInstance().format(number);
		return formattedNumber;
	}

	private static final String ALGORITHM = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128; // 인증 태그 길이
	private static final int IV_LENGTH_BYTE = 12;  // GCM 권장 IV 길이

	public static byte[] generate32ByteKey(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(input.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}

	// 1. 암호화
	public static String encrypt(String plainText, String secretKey) throws Exception {
		byte[] iv = new byte[IV_LENGTH_BYTE];
		new SecureRandom().nextBytes(iv); // 무작위 IV 생성

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKey keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
		GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
		byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

		// 복호화 시 IV가 필요하므로 IV + 암호문을 합쳐서 Base64로 저장
		byte[] combined = new byte[iv.length + cipherText.length];
		System.arraycopy(iv, 0, combined, 0, iv.length);
		System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

		return Base64.getUrlEncoder().withoutPadding().encodeToString(combined);
	}

	// 2. 복호화
	public static String decrypt(String encryptedText, String secretKey) throws Exception {
		byte[] decoded = Base64.getUrlDecoder().decode(encryptedText);

		// 앞부분에서 IV 추출
		byte[] iv = new byte[IV_LENGTH_BYTE];
		System.arraycopy(decoded, 0, iv, 0, iv.length);

		// 뒷부분에서 실제 암호문 추출
		byte[] cipherText = new byte[decoded.length - IV_LENGTH_BYTE];
		System.arraycopy(decoded, IV_LENGTH_BYTE, cipherText, 0, cipherText.length);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKey keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
		GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
		byte[] plainText = cipher.doFinal(cipherText);

		return new String(plainText, StandardCharsets.UTF_8);
	}

}