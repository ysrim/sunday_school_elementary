package com.base.utl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class VapidKeyGenerator {

	public static void main(String[] args) throws Exception {

		// 1. 보안 제공자 등록
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}

		// 2. 키 쌍 생성 (prime256v1 / P-256)
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH", "BC");
		keyPairGenerator.initialize(new java.security.spec.ECGenParameterSpec("prime256v1"));
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 3. Public Key 추출 (Uncompressed format - 65 bytes)
		ECPublicKey publicKey = (ECPublicKey)keyPair.getPublic();
		byte[] encodedPublicKey = ((org.bouncycastle.jce.interfaces.ECPublicKey)publicKey).getQ().getEncoded(false);

		// 4. Private Key 추출 (32 bytes)
		ECPrivateKey privateKey = (ECPrivateKey)keyPair.getPrivate();
		byte[] encodedPrivateKey = privateKey.getS().toByteArray();

		// Private Key가 33바이트로 나올 경우(부호 비트 때문) 앞의 0을 제거하여 32바이트로 맞춤
		if (encodedPrivateKey.length == 33 && encodedPrivateKey[0] == 0) {
			byte[] tmp = new byte[32];
			System.arraycopy(encodedPrivateKey, 1, tmp, 0, 32);
			encodedPrivateKey = tmp;
		}

		// 5. 결과 출력 (Base64 URL Safe)
		System.out.println("===========================================");
		System.out.println("PWA VAPID KEYS GENERATED");
		System.out.println("===========================================");
		System.out.println("Public Key (JavaScript & yml):");
		System.out.println(Base64.getUrlEncoder().withoutPadding().encodeToString(encodedPublicKey));
		System.out.println("\nPrivate Key (yml only):");
		System.out.println(Base64.getUrlEncoder().withoutPadding().encodeToString(encodedPrivateKey));
		System.out.println("===========================================");

	}

}