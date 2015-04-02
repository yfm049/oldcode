package com.xinxi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Coder {
	/**
	 * 73. * SHAº”√‹ 74. * 75. *
	 * 
	 * @param data
	 *            76. *
	 * @return 77. *
	 * @throws Exception
	 *             78.
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(data);
		return sha.digest();

	}

	private static final String ENCODE = "unicode";
	private static MessageDigest sha1MD;

	public static String SHA1(String text) {

		if (null == sha1MD) {

			try {

				sha1MD = MessageDigest.getInstance("SHA-1");

			} catch (NoSuchAlgorithmException e) {

				return null;

			}

		}

		try {

			sha1MD.update(text.getBytes(ENCODE), 0, text.length());

		} catch (UnsupportedEncodingException e) {

			sha1MD.update(text.getBytes(), 0, text.length());

		}

		return toHexString(sha1MD.digest());

	}

	public static byte[] SHA1BYTE(String text) {

		if (null == sha1MD) {

			try {

				sha1MD = MessageDigest.getInstance("SHA-1");

			} catch (NoSuchAlgorithmException e) {

				return null;

			}

		}

		try {

			sha1MD.update(text.getBytes(ENCODE), 0, text.length());

		} catch (UnsupportedEncodingException e) {

			sha1MD.update(text.getBytes(), 0, text.length());

		}

		return sha1MD.digest();

	}

	private static String toHexString(byte[] hashs) {

		StringBuffer sBuffer = new StringBuffer();

		for (byte hash : hashs) {

			sBuffer.append(Integer.toString((hash & 0xFF) + 0x100, 16)
					.substring(1));

		}

		return sBuffer.toString();

	}

}
