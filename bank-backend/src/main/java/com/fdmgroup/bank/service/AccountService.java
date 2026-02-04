package com.fdmgroup.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;
import com.fdmgroup.bank.model.AccountType;
import com.fdmgroup.bank.model.CheckingAccount;
import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;
import com.fdmgroup.bank.model.SavingsAccount;
import com.fdmgroup.bank.repository.AccountRepository;

@Service
public class AccountService implements IAccountService
{
	private AccountRepository accountRepository;
	private ICustomerService customerService;
	
	public AccountService(AccountRepository accountRepository, ICustomerService customerService)
	{
		super();
		this.accountRepository = accountRepository;
		this.customerService = customerService;
	}
	
	@Override
	public List<Account> getAccounts()
	{
		System.out.println("Account Service: Retrieving all accounts");
		return accountRepository.findAll();
	}

	@Override
	public Account getAccount(long id)
	{
		System.out.println("Account Service: Retrieving account with ID: " + id);
		Optional<Account> account = accountRepository.findById(id);
		return account.orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
	}
	
	@Override
	public Account createAccount(AccountDTO dto)
	{
		System.out.println("Account Service: Creating account");
		Account account;
		
		Customer customer = customerService.getCustomer(dto.getCustomerId());
		
		if (dto.getAccountType().equalsIgnoreCase(AccountType.CHECKING_STRING))
			account = new CheckingAccount(dto.getBalance(), customer, dto.getNextCheckNumber());
		else if (dto.getAccountType().equalsIgnoreCase(AccountType.SAVINGS_STRING))
			account = new SavingsAccount(dto.getBalance(), customer, dto.getInterestRate());
		else
			throw new AccountTypeInvalidException("Invalid account type: " + dto.getAccountType());
		
		return accountRepository.save(account);
	}
	
	@Override
	public Account updateAccount(long id, AccountDTO dto)
	{
		System.out.println("Account Service: Updating account with ID: " + id);
		if (!accountRepository.existsById(id))
			throw new AccountNotFoundException("Account not found with id: " + id);
		
		Account account;
		
		Customer customer = customerService.getCustomer(dto.getCustomerId());
		
		if (dto.getAccountType().equalsIgnoreCase(AccountType.CHECKING_STRING))
			account = new CheckingAccount(id, dto.getBalance(), customer, dto.getNextCheckNumber());
		else if (dto.getAccountType().equalsIgnoreCase(AccountType.SAVINGS_STRING))
			account = new SavingsAccount(id, dto.getBalance(), customer, dto.getInterestRate());
		else
			throw new AccountTypeInvalidException("Invalid account type: " + dto.getAccountType());
		
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(long id)
	{
		System.out.println("Account Service: Deleting account with ID: " + id);
		if (!accountRepository.existsById(id))
			throw new AccountNotFoundException("Account not found with id: " + id);
		
		Account account = getAccount(id);
		CustomerDTO dto = new CustomerDTO(account.getCustomer());
		dto.getAccountIds().remove(id);
		customerService.updateCustomer(account.getCustomer().getCustomerId(), dto);
	}

}
