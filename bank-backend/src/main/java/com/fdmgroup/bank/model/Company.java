package com.fdmgroup.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(CustomerType.COMPANY_STRING)
public class Company extends Customer
{
	/**
	 * Default constructor for JPA. Do not use directly. Use the other constructors instead.
	 */
	public Company()
	{
		super();
	}
	
	/**
	 * @param name
	 * @param address
	 */
	public Company(String name, Address address)
	{
		super(name, address);
	}

	/**
	 * @param customerId
	 * @param name
	 * @param address
	 */
	public Company(long customerId, String name, Address address)
	{
		super(customerId, name, address);
	}
}
