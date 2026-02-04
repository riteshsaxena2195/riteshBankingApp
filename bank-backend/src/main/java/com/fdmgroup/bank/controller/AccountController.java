package com.fdmgroup.bank.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.fdmgroup.bank.model.Account;
import com.fdmgroup.bank.model.AccountDTO;
import com.fdmgroup.bank.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin(origins = "https://my-react-app-135897197036.us-central1.run.app", allowedHeaders = "*")
public class AccountController
{
	private IAccountService service;
	
	public AccountController(IAccountService service)
	{
		super();
		this.service = service;
	}
	
	@Operation(summary = "Get all accounts", description = "Returns a list of all accounts in the system")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved list of accounts"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAccounts()
	{
		System.out.println("Account Controller: Fetching all accounts");
		List<AccountDTO> accounts = service.getAccounts().stream().map(AccountDTO::new).toList();
		return ResponseEntity.ok(accounts);
	}
	
	@Operation(summary = "Get a account by ID", description = "Returns a account with the specified ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved account",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the retrieved account") }),
		@ApiResponse(responseCode = "404", description = "Account not found"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable long id)
	{
		System.out.println("Account Controller: Fetching account with ID " + id);
		Account account = service.getAccount(id);
		return ResponseEntity.ok(new AccountDTO(account));
	}
	
	@Operation(summary = "Create a new account", description = "Creates a new account and returns created account")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Successfully created account",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the created account") }),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountDTO account)
	{
		System.out.println("Account Controller: Creating new account");
		Account created = service.createAccount(account);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(created.getAccountId()).toUri();
		
		return ResponseEntity.created(location).body(created);
	}
	
	@Operation(summary = "Update an existing account", description = "Updates existing account with the specified ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully updated account",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the updated account") }),
		@ApiResponse(responseCode = "404", description = "Account not found"),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody AccountDTO account)
	{
		System.out.println("Account Controller: Updating account with ID " + id);
		Account updated = service.updateAccount(id, account);
		return ResponseEntity.ok(updated);
	}
	
	@Operation(summary = "Delete a account", description = "Deletes a account with the specified ID")
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable long id)
	{
		System.out.println("Account Controller: Deleting account with ID " + id);
		service.deleteAccount(id);
	}
}