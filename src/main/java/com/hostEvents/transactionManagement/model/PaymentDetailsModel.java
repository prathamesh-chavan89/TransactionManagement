package com.hostEvents.transactionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsModel {
	private String amount;
	private String eventId;
	private String payemntMethod;
	private String userId;

}
