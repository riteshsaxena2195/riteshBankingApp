package com.fdmgroup.bank.service;

import java.util.List;

import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;

public interface IManagementService
{
	void addCustomerAccount(long customerId, long accountId);
	
	void removeCustomerAccount(long customerId, long accountId);
	
	List<Account> getCustomerAccountsByCity(String city);
	
	List<AccountDTO> getCustomerAccounts(long customerId);
}
