package com.fdmgroup.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{
	
}
