package com.fdmgroup.bank.model;

public enum CustomerType
{
	COMPANY,
	PERSON;
	
	public static final String COMPANY_STRING = "Company";
	public static final String PERSON_STRING = "Person";
	
	public static final Class<?> COMPANY_CLASS = Company.class;
	public static final Class<?> PERSON_CLASS = Person.class;
}
