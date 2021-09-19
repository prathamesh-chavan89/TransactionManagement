package com.hostEvents.transactionManagement.graphql;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hostEvents.transactionManagement.model.PaymentOrderModel;
import com.hostEvents.transactionManagement.model.PaymentOrderResponse;
import com.hostEvents.transactionManagement.service.TransactionService;
import com.hostEvents.transactionManagement.util.Response;
import com.hostEvents.transactionManagement.util.TransactionConstants;

@Service
public class TransactionMutation implements GraphQLMutationResolver {

	private final TransactionService transactionService;

	@Autowired
	public TransactionMutation(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public PaymentOrderResponse generateOrder(PaymentOrderModel paymentOrderModel) {
		PaymentOrderResponse paymentOrderResponse = new PaymentOrderResponse();
		Response response = transactionService.generatePaymentOrder(paymentOrderModel);
		if (response.getStatus().equalsIgnoreCase(TransactionConstants.Response.SUCCESS)) {
			BeanUtils.copyProperties(response.getDetails(), paymentOrderResponse);
		}
		paymentOrderResponse.setErrorMsg(response.getResponseMessage());
		paymentOrderResponse.setStauts(response.getStatus());
		return paymentOrderResponse;
	}
}
