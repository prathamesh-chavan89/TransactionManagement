package com.hostEvents.transactionManagement.service;

import java.util.Map;

import com.hostEvents.transactionManagement.model.PaymentOrderModel;
import com.hostEvents.transactionManagement.util.Response;

public interface TransactionService {
	public Response generatePaymentOrder(PaymentOrderModel paymentOrderModel);

	public Response updatePaymentDetails(Map<String, Object> requestData);

}
