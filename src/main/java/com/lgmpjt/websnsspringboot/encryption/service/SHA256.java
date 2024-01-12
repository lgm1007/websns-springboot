package com.lgmpjt.websnsspringboot.encryption.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SHA256 {

	private final static String SHA_256 = "SHA-256";

	public String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
		messageDigest.update(text.getBytes());

		return bytesToHex(messageDigest.digest());
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
