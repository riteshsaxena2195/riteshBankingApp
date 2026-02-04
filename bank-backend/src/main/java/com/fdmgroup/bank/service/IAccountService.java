package com.fdmgroup.bank.service;

import java.util.List;

import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;

public interface IAccountService
{
	List<Account> getAccounts();
	
	Account getAccount(long id);
	
	Account createAccount(AccountDTO dto);
	
	Account updateAccount(long id, AccountDTO dto);
	
	void deleteAccount(long id);
}
