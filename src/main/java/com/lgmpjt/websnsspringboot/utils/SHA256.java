package com.lgmpjt.websnsspringboot.utils;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SHA256 {

	private final static String SHA_256 = "SHA-256";

	public static String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
		messageDigest.update(text.getBytes());

		return bytesToHex(messageDigest.digest());
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
