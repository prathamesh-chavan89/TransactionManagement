package com.hostEvents.transactionManagement.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.hostEvents.transactionManagement.exception.PaymentException;

public class RazorpayUtil {

	public static boolean verifyPaymentSignature(String expectedSignature, String orderId, String paymentId,
			String apiSecret) throws PaymentException {
		String payload = orderId + '|' + paymentId;
		return verifySignature(payload, expectedSignature, apiSecret);
	}

	public static boolean verifyWebhookSignature(String payload, String expectedSignature, String webhookSecret)
			throws PaymentException {
		return verifySignature(payload, expectedSignature, webhookSecret);
	}

	public static boolean verifySignature(String payload, String expectedSignature, String secret)
			throws PaymentException {
		String actualSignature = getHash(payload, secret);
		return isEqual(actualSignature.getBytes(), expectedSignature.getBytes());
	}

	public static String getHash(String payload, String secret) throws PaymentException {
		Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			sha256_HMAC.init(secretKey);
			byte[] hash = sha256_HMAC.doFinal(payload.getBytes());
			return new String(Hex.encodeHex(hash));
		} catch (Exception e) {
			throw new PaymentException(e.getMessage());
		}
	}

	/**
	 * We are not using String.equals() method because of security issue mentioned
	 * in <a href="http://security.stackexchange.com/a/83670">StackOverflow</a>
	 *
	 * @param a
	 * @param b
	 * @return boolean
	 */
	private static boolean isEqual(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}
		return result == 0;
	}
}
