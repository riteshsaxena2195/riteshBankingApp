package com.fdmgroup.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 4028259883741354078L;

	public CustomerNotFoundException(String message)
	{
		super(message);
	}
}
