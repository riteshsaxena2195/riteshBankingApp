package com.fdmgroup.bank.model;

public class AccountDTO
{
	private Long accountId;
	private String accountType;
	private double balance;
	private Long customerId;
	private int nextCheckNumber;
	private double interestRate;
	
	public AccountDTO()
	{
		super();
	}
	
	public AccountDTO(Account account)
	{
		super();
		this.accountId = account.getAccountId();
		this.accountType = account instanceof CheckingAccount ? AccountType.CHECKING_STRING : AccountType.SAVINGS_STRING;
		this.balance = account.getBalance();
		this.customerId = account.getCustomer() != null ? account.getCustomer().getCustomerId() : null;
		if (account instanceof CheckingAccount checking)
			this.nextCheckNumber = checking.getNextCheckNumber();
		else if (account instanceof SavingsAccount savings)
			this.interestRate = savings.getInterestRate();
		}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String type) {
		this.accountType = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getNextCheckNumber() {
		return nextCheckNumber;
	}

	public void setNextCheckNumber(int nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
