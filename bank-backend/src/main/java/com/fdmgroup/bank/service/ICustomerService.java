package com.fdmgroup.bank.service;

import java.util.List;

import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;

public interface ICustomerService
{
	List<Customer> getCustomers();
	
	Customer getCustomer(long id);
	
	Customer createCustomer(CustomerDTO dto);
	
	Customer updateCustomer(long id, CustomerDTO dto);
	
	void deleteCustomer(long id);
}
