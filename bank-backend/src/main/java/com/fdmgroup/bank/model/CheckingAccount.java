package com.fdmgroup.bank.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;

@Entity
@DiscriminatorValue(AccountType.CHECKING_STRING)
public class CheckingAccount extends Account
{
	@Positive
	@Column(name = "NEXT_CHECK_NUMBER", nullable = false)
	private int nextCheckNumber;

	public CheckingAccount()
	{
		super();
	}

	public CheckingAccount(double balance, Customer customer, int nextCheckNumber)
	{
		super(balance, customer);
		this.nextCheckNumber = nextCheckNumber;
	}

	public CheckingAccount(long id, double balance, Customer customer, int nextCheckNumber)
	{
		super(id, balance, customer);
		this.nextCheckNumber = nextCheckNumber;
	}

	public int getNextCheckNumber()
	{
		return nextCheckNumber;
	}

	public void setNextCheckNumber(int nextCheckNumber)
	{
		this.nextCheckNumber = nextCheckNumber;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nextCheckNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj) || getClass() != obj.getClass())
			return false;
		CheckingAccount other = (CheckingAccount) obj;
		return nextCheckNumber == other.nextCheckNumber;
	}
}
