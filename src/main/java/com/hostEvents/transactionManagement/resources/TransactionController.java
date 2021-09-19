package com.hostEvents.transactionManagement.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostEvents.transactionManagement.model.PaymentOrderModel;
import com.hostEvents.transactionManagement.service.TransactionService;
import com.hostEvents.transactionManagement.util.Response;

@CrossOrigin
@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/generateOrder")
	public Response generateOrder(@RequestBody PaymentOrderModel paymentOrderModel) {
		return transactionService.generatePaymentOrder(paymentOrderModel);
	}

	@PostMapping("/updatePaymentDetails")
	public Response updatePaymentDetails(@RequestBody Map<String, Object> requestData) {
		return null;
	}

	@GetMapping("/getInvoices")
	public List<Object> getInvoicesOfUser(@RequestParam("userId") String userId) {
		return null;
	}
}
