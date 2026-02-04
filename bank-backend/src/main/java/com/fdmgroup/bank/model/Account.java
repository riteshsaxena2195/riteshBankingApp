package com.fdmgroup.bank.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "accountType"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CheckingAccount.class, name = AccountType.CHECKING_STRING),
	@JsonSubTypes.Type(value = SavingsAccount.class, name = AccountType.SAVINGS_STRING)
})
@JsonPropertyOrder({
	"id",
	"accountType",
	"balance"
})
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "accountId"
)
@SequenceGenerator(
	name = "ACCOUNT_GEN",
	sequenceName = "ACCOUNT_SEQ",
	initialValue = 1001
)
@DiscriminatorColumn(
	name = "ACCOUNT_TYPE",
	discriminatorType = DiscriminatorType.STRING,
	length = 20
)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Account
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_GEN")
	@Column(name = "ACCOUNT_ID")
	private long accountId;
	
	@Column(name = "BALANCE", nullable = false)
	private double balance;
	
	@ManyToOne
	@JoinColumn(name = "FK_CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
	//@JsonBackReference(value = "customer-accounts")
	@JsonIgnoreProperties("accounts")
	private Customer customer;

	public Account()
	{
		super();
	}

	public Account(double balance, Customer customer)
	{
		super();
		this.balance = balance;
		this.customer = customer;
	}

	public Account(long id, double balance, Customer customer)
	{
		super();
		this.accountId = id;
		this.balance = balance;
		this.customer = customer;
	}

	public long getAccountId()
	{
		return accountId;
	}

	public void setAccountId(long accountId)
	{
		this.accountId = accountId;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(accountId, balance, customer);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountId == other.accountId
			&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
			&& Objects.equals(customer, other.customer);
	}
}
