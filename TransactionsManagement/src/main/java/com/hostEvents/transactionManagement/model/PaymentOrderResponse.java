package com.hostEvents.transactionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderResponse {
	private String amount;
	private String currency;
	private String customerId;
	private String errorMsg;
	private String eventId;
	private String orderId;
	private Integer paymentCapture = 1;
	private String paymentGateway;
	private String receiptId;
	private String stauts;
	private String ticketType;
}
