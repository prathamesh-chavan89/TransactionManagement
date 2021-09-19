package com.hostEvents.transactionManagement.exception;

import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = -7619598881153575210L;

	private String message;

	public PaymentException(String message) {
		this.message = message;
	}
}
