package com.fdmgroup.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.bank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	
}
