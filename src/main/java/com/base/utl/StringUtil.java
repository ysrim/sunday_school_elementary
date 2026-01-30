package com.base.utl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {

	private static final String ALGORITHM = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;
	private static final String KOREA_ZONE = "Asia/Seoul";

	// 인스턴스화 방지
	private StringUtil() {
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * 숫자에 콤마 추가 (null 안전)
	 */
	public static String comma(Integer number) {
		return NumberFormat.getInstance().format(Optional.ofNullable(number).orElse(0));
	}

	/**
	 * AES-GCM 암호화
	 */
	public static String encrypt(String plainText, String secretKey) {
		if (plainText == null || secretKey == null)
			return null;

		try {
			byte[] iv = new byte[IV_LENGTH_BYTE];
			new SecureRandom().nextBytes(iv);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKey keySpec = generateKey(secretKey);
			GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

			cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

			byte[] combined = new byte[iv.length + cipherText.length];
			System.arraycopy(iv, 0, combined, 0, iv.length);
			System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

			return Base64.getUrlEncoder().withoutPadding().encodeToString(combined);
		} catch (Exception e) {
			log.error("Encryption error: {}", e.getMessage());
			throw new RuntimeException("암호화 중 오류가 발생했습니다.", e);
		}
	}

	/**
	 * AES-GCM 복호화
	 */
	public static String decrypt(String encryptedText, String secretKey) {
		if (encryptedText == null || secretKey == null)
			return null;

		try {
			byte[] decoded = Base64.getUrlDecoder().decode(encryptedText);
			if (decoded.length < IV_LENGTH_BYTE)
				throw new IllegalArgumentException("Invalid cipher text");

			byte[] iv = new byte[IV_LENGTH_BYTE];
			System.arraycopy(decoded, 0, iv, 0, iv.length);

			byte[] cipherText = new byte[decoded.length - IV_LENGTH_BYTE];
			System.arraycopy(decoded, IV_LENGTH_BYTE, cipherText, 0, cipherText.length);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKey keySpec = generateKey(secretKey);
			GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

			cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("Decryption error: {}", e.getMessage());
			throw new RuntimeException("복호화 중 오류가 발생했습니다.", e);
		}
	}

	/**
	 * 키 길이 제한 문제를 해결하기 위한 비밀키 생성 로직
	 */
	private static SecretKey generateKey(String password) {
		byte[] keyBytes = new byte[16]; // 128비트 고정
		byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
		System.arraycopy(passwordBytes, 0, keyBytes, 0, Math.min(passwordBytes.length, keyBytes.length));
		return new SecretKeySpec(keyBytes, "AES");
	}

	/**
	 * 오늘이 일요일인지 확인
	 */
	public static boolean isTodaySunday() {
		return isDayOfWeek(LocalDate.now(ZoneId.of(KOREA_ZONE)), DayOfWeek.SUNDAY);
	}

	/**
	 * 특정 날짜의 요일 확인 (확장성)
	 */
	public static boolean isDayOfWeek(LocalDate date, DayOfWeek dayOfWeek) {
		return date != null && date.getDayOfWeek() == dayOfWeek;
	}

	/**
	 * 두 날짜 차이(date2-date1)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDateDiff(String date1, String date2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return ChronoUnit.DAYS.between(LocalDate.parse(date2, formatter), LocalDate.parse(date1, formatter));
	}

	/**
	 * 두 날짜 차이(오늘날짜-date1)
	 * @param date1
	 * @return
	 */
	public static boolean getDateDiffToday(String date1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		long betweenDay = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(date1, formatter));
		try {
			int intValue = Math.toIntExact(betweenDay);
			return intValue == 1;
		} catch (ArithmeticException e) {
			log.error("error: {}", e.getMessage());
		}
		return false;
	}

}