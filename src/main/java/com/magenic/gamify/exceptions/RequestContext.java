package com.magenic.gamify.exceptions;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

//https://stackoverflow.com/questions/43502332/how-to-get-the-requestbody-in-an-exceptionhandler-spring-rest
@ManagedBean
@RequestScope
public class RequestContext {
	private Object requestBody;

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
}
