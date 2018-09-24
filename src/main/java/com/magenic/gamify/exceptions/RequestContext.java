package com.magenic.gamify.exceptions;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

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
