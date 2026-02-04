package com.fdmgroup.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(CustomerType.PERSON_STRING)
public class Person extends Customer
{
	/**
	 * Default constructor for JPA. Do not use directly. Use the other constructors instead.
	 */
	public Person()
	{
		super();
	}
	
	/**
	 * @param name
	 * @param address
	 */
	public Person(String name, Address address)
	{
		super(name, address);
	}

	/**
	 * @param customerId
	 * @param name
	 * @param address
	 */
	public Person(long customerId, String name, Address address)
	{
		super(customerId, name, address);
	}
}
