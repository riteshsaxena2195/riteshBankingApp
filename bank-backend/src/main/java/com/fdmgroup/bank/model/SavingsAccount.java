package com.fdmgroup.bank.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@DiscriminatorValue(AccountType.SAVINGS_STRING)
public class SavingsAccount extends Account
{
	@PositiveOrZero
	@Column(name = "INTEREST_RATE", nullable = false)
	private double interestRate;

	public SavingsAccount()
	{
		super();
	}

	public SavingsAccount(double balance, Customer customer, double interestRate)
	{
		super(balance, customer);
		this.interestRate = interestRate;
	}

	public SavingsAccount(long id, double balance, Customer customer, double interestRate)
	{
		super(id, balance, customer);
		this.interestRate = interestRate;
	}

	public double getInterestRate()
	{
		return interestRate;
	}

	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(interestRate);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj) || getClass() != obj.getClass())
			return false;
		SavingsAccount other = (SavingsAccount) obj;
		return Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate);
	}
}
