package com.fdmgroup.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.Address;
import com.fdmgroup.bank.model.Company;
import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;
import com.fdmgroup.bank.model.CustomerType;
import com.fdmgroup.bank.model.Person;
import com.fdmgroup.bank.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService implements ICustomerService
{
	private IAddressClient addressClient;
	private CustomerRepository customerRepository;
	
	public CustomerService(IAddressClient addressClient, CustomerRepository customerRepository)
	{
		super();
		this.addressClient = addressClient;
		this.customerRepository = customerRepository;
	}
	
	@Override
	public List<Customer> getCustomers()
	{
		System.out.println("Customer Service: Retrieving all customers");
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(long id)
	{
		System.out.println("Customer Service: Retrieving customer with ID: " + id);
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElseThrow(() -> new CustomerNotFoundException("Customer with id=" + id + " not found"));
	}
	
	@Transactional
	@Override
	public Customer createCustomer(CustomerDTO dto)
	{
		System.out.println("Customer Service: Creating customer");
		Address address = new Address(dto.getStreetNumber(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
		Customer customer;

		if (dto.getCustomerType().equalsIgnoreCase(CustomerType.PERSON_STRING))
			customer = new Person(dto.getName(), address);
		else if (dto.getCustomerType().equalsIgnoreCase(CustomerType.COMPANY_STRING))
			customer = new Company(dto.getName(), address);
		else
			throw new CustomerTypeInvalidException("Invalid customer type: " + dto.getCustomerType());
		
		customer.setAddress(addressClient.getAddress(address));
		return customerRepository.save(customer);
	}

	@Transactional
	@Override
	public Customer updateCustomer(long id, CustomerDTO dto)
	{
		System.out.println("Customer Service: Updating customer with ID: " + id);
		Customer customerRecord = getCustomer(id);
		
		if (dto.getName() != null && !dto.getName().isBlank())
			customerRecord.setName(dto.getName());
		
		if ((dto.getStreetNumber() != null && !dto.getStreetNumber().isBlank()) ||
			(dto.getCity() != null && !dto.getCity().isBlank()) ||
			(dto.getProvince() != null && !dto.getProvince().isBlank()) ||
			(dto.getPostalCode() != null && !dto.getPostalCode().isBlank()))
		{
			Address address = new Address(customerRecord.getAddress().getId(),
				dto.getStreetNumber(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
			customerRecord.setAddress(addressClient.getAddress(address));
		}
		
		customerRecord.getAccounts().removeIf(account -> !dto.getAccountIds().contains(account.getAccountId()));
		
		return customerRepository.save(customerRecord);
	}

	@Override
	public void deleteCustomer(long id)
	{
		System.out.println("Customer Service: Deleting customer with ID: " + id);
		customerRepository.deleteById(id);
	}
}
