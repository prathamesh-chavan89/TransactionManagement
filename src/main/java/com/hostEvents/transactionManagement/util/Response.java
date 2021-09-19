package com.hostEvents.transactionManagement.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private String status;
	private String responseMessage;
	private Object details;
}
