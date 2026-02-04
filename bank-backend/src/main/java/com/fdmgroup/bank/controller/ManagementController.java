package com.fdmgroup.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;
import com.fdmgroup.bank.service.IManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController
{
	private IManagementService managementService;
	
	public ManagementController(IManagementService managementService)
	{
		super();
		this.managementService = managementService;
	}

	@Operation(
		summary = "Add an account to a customer",
		description = "Adds an account to a customer by their IDs"
	)
	@PostMapping("/addCustomerAccount")
	public void addCustomerAccount(@RequestParam long customerId, @RequestParam long accountId)
	{
		managementService.addCustomerAccount(customerId, accountId);
	}

	@Operation(
		summary = "Removes an account from a customer",
		description = "Removes an account from a customer by their IDs"
	)
	@PostMapping("/removeCustomerAccount")
	public void removeCustomerAccount(@RequestParam long customerId, @RequestParam long accountId)
	{
		managementService.removeCustomerAccount(customerId, accountId);
	}

	@Operation(
		summary = "Get accounts held by customers in a city",
		description = "Returns a list of all accounts held by customers in a specific city"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved list of accounts"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getCustomerAccountsByCity")
	public ResponseEntity<List<Account>> getCustomerAccountsByCity(@RequestParam String city)
	{
		List<Account> accounts = managementService.getCustomerAccountsByCity(city);
		return ResponseEntity.ok(accounts);
	}

	@Operation(
		summary = "Get accounts held by customers in Toronto",
		description = "Returns a list of all accounts held by customers in a Toronto"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved list of accounts"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getTorontoAccounts")
	public ResponseEntity<List<Account>> getTorontoAccounts()
	{
		List<Account> accounts = managementService.getCustomerAccountsByCity("Toronto");
		return ResponseEntity.ok(accounts);
	}

	@Operation(
		summary = "Get accounts held by a specific customer",
		description = "Returns a list of all accounts held by a specific customer"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved list of accounts"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getCustomerAccounts/{customerId}")
	public ResponseEntity<List<AccountDTO>> getCustomerAccounts(@PathVariable long customerId)
	{
		List<AccountDTO> accounts = managementService.getCustomerAccounts(customerId);
		return ResponseEntity.ok(accounts);
	}
}
