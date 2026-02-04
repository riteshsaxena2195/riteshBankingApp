package com.fdmgroup.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;
import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;

@Service
public class ManagementService implements IManagementService
{
	private CustomerService customerService;
	private AccountService accountService;
	
	public ManagementService(CustomerService customerService, AccountService accountService)
	{
		super();
		this.customerService = customerService;
		this.accountService = accountService;
	}
	
	public void addCustomerAccount(long customerId, long accountId)
	{
		Customer customer = customerService.getCustomer(customerId);
		Account account = accountService.getAccount(accountId);
		
		account.setCustomer(customer);
		customer.getAccounts().add(account);
		customerService.updateCustomer(customer.getCustomerId(), new CustomerDTO(customer));
	}
	
	public void removeCustomerAccount(long customerId, long accountId)
	{
		Customer customer = customerService.getCustomer(customerId);
		Account account = accountService.getAccount(accountId);
		
		account.setCustomer(null);
		customer.getAccounts().remove(account);
		customerService.updateCustomer(customer.getCustomerId(), new CustomerDTO(customer));
		accountService.deleteAccount(account.getAccountId());
	}
	
	public List<Account> getCustomerAccountsByCity(String city)
	{
		return customerService.getCustomers()
			.stream()
			.filter(customer -> customer.getAddress().getCity().equalsIgnoreCase(city))
			.flatMap(customer -> customer.getAccounts().stream())
			.toList();
	}
	
	public List<AccountDTO> getCustomerAccounts(long customerId)
	{
		Customer customer = customerService.getCustomer(customerId);
		return customer.getAccounts().stream().map(AccountDTO::new).toList();
	}
}
