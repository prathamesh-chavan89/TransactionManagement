package com.hostEvents.transactionManagement.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hostEvents.transactionManagement.entity.EventMasterEntity;
import com.hostEvents.transactionManagement.model.PaymentOrderModel;
import com.hostEvents.transactionManagement.model.PaymentOrderResponse;
import com.hostEvents.transactionManagement.repo.EeventMasterRepository;
import com.hostEvents.transactionManagement.service.TransactionService;
import com.hostEvents.transactionManagement.util.Response;
import com.hostEvents.transactionManagement.util.TransactionConstants;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class TransactionServiceImpl implements TransactionService, GraphQLQueryResolver, GraphQLMutationResolver {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Value("${razorpay.key}")
	private String razorPayKey;

	@Value("${razorpay.secret}")
	private String razorPaySecretKey;

	@Autowired
	private EeventMasterRepository eventMasterRepository;

	@Override
	public Response generatePaymentOrder(PaymentOrderModel paymentOrderModel) {

		EventMasterEntity eventMasterEntity = null;
		PaymentOrderResponse paymentOrderResponse = new PaymentOrderResponse();
		logger.info("Start : In method generateOrder and class {},/n request data is :{}", getClass().getName(),
				paymentOrderModel);
		try {
			if ((paymentOrderModel.getAmount() == null) || (paymentOrderModel.getAmount().isEmpty())) {
				logger.info("###Error### In method generateOrder and class {},/n Amount is not Provided",
						getClass().getName());
				return responseBuilder(TransactionConstants.Response.ERROR, "Amount not provided", null);
			}
			Optional<EventMasterEntity> opEventMasterEntity = eventMasterRepository
					.findById(new BigInteger(paymentOrderModel.getEventId()));

			if (!opEventMasterEntity.isPresent()) {
				logger.info("###Error#### In method generateOrder and class {},/n Event data is not present",
						getClass().getName());
				return responseBuilder(TransactionConstants.Response.ERROR,
						"Event Id is Not Present or Event is cancelled", null);
			}
			eventMasterEntity = opEventMasterEntity.get();
			BigDecimal amount = new BigDecimal(paymentOrderModel.getAmount());
//			if (eventMasterEntity.getTicketPrise().compareTo(amount) != 0) {
//				return responseBuilder(TransactionConstants.Response.ERROR,
//						"Event Ticket prise and entered amount not matching", null);
//			}

			RazorpayClient client = new RazorpayClient(razorPayKey, razorPaySecretKey);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(TransactionConstants.RazorPay.AMOUNT, paymentOrderModel.getAmount());
			jsonObject.put(TransactionConstants.RazorPay.CURRENCY, paymentOrderModel.getCurrency());
			jsonObject.put(TransactionConstants.RazorPay.RECEIPT, generateReceiptId(paymentOrderModel));
			jsonObject.put(TransactionConstants.RazorPay.PAYMENT_CAPTURE, paymentOrderModel.getPaymentCapture());
			Order order = client.Orders.create(jsonObject);
			paymentOrderResponse.setOrderId(order.get(TransactionConstants.RazorPay.ID));
		} catch (RazorpayException e) {
			e.printStackTrace();
			logger.info("###Error#### RazorpayException In method generateOrder and class {}", getClass().getName());
			return responseBuilder(TransactionConstants.Response.ERROR, "Error while Creating Payment Order", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("###Error#### Exception In method generateOrder and class {}", getClass().getName());
			return responseBuilder(TransactionConstants.Response.ERROR, "Error While creating Payment Order", null);
		}
		logger.info("Order generated sucessfully Order id is {}", paymentOrderResponse.getOrderId());
		return responseBuilder(TransactionConstants.Response.SUCCESS, "Payment Order Created Successfully",
				paymentOrderResponse);
	}

	private Response responseBuilder(String responseType, String responseMsg, Object responseDetails) {
		return new Response(responseType, responseMsg, responseDetails);
	}

	private String generateReceiptId(PaymentOrderModel paymentOrderModel) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(paymentOrderModel.getEventId() + "_");
		stringBuilder.append(paymentOrderModel.getCustomerId() + "_");
		stringBuilder.append(paymentOrderModel.getCurrency() + "_");
		stringBuilder.append(paymentOrderModel.getAmount());
		logger.debug("Generate Receipt id is {}", stringBuilder);
		return stringBuilder.toString();
	}

	@Override
	public Response updatePaymentDetails(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		return null;
	}

}
