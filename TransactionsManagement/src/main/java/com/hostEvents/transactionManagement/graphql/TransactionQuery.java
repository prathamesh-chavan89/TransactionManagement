package com.hostEvents.transactionManagement.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class TransactionQuery implements GraphQLQueryResolver {

	public String getOrderId() {
		return "success...!";
	}
}
