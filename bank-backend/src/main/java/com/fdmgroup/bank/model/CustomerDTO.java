package com.fdmgroup.bank.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO
{
	private long customerId;
	private String customerType;
	private String name;
	private String streetNumber;
	private String city;
	private String province;
	private String postalCode;
	private List<Long> accountIds;
	
	public CustomerDTO()
	{
		super();
		this.accountIds = new ArrayList<>();
	}
	
	public CustomerDTO(Customer customer)
	{
		super();
		this.customerId = customer.getCustomerId();
		this.customerType = customer instanceof Person ? CustomerType.PERSON_STRING : CustomerType.COMPANY_STRING;
		this.name = customer.getName();
		this.streetNumber = customer.getAddress().getStreetNumber();
		this.city = customer.getAddress().getCity();
		this.province = customer.getAddress().getProvince();
		this.postalCode = customer.getAddress().getPostalCode();
		this.accountIds = new ArrayList<>();
		
		if (customer.getAccounts() != null)
			for (Account account : customer.getAccounts())
				accountIds.add(account.getAccountId());
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String type) {
		this.customerType = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", customerType=" + customerType + ", name=" + name
				+ ", streetNumber=" + streetNumber + ", city=" + city + ", province=" + province + ", postalCode="
				+ postalCode + ", accountIds=" + accountIds + "]";
	}
}
