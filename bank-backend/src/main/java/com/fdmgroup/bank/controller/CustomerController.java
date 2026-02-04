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


import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;
import com.fdmgroup.bank.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "https://my-react-app-135897197036.us-central1.run.app", allowedHeaders = "*")
public class CustomerController
{
	private ICustomerService service;
	
	public CustomerController(ICustomerService service)
	{
		super();
		this.service = service;
	}
	
	@Operation(summary = "Get all customers", description = "Returns a list of all customers in the system")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved list of customers"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getCustomers()
	{
		List<CustomerDTO> customers = service.getCustomers().stream().map(CustomerDTO::new).toList();
		return ResponseEntity.ok(customers);
	}
	
	@Operation(summary = "Get a customer by ID", description = "Returns a customer with the specified ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved customer",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the retrieved customer") }),
		@ApiResponse(responseCode = "404", description = "Customer not found"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable long id)
	{
		CustomerDTO customer = new CustomerDTO(service.getCustomer(id));
		return ResponseEntity.ok(customer);
	}
	
	@Operation(summary = "Create a new customer", description = "Creates a new customer and returns created customer")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Successfully created customer",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the created customer") }),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customer)
	{
		Customer created = service.createCustomer(customer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(created.getCustomerId()).toUri();
		
		return ResponseEntity.created(location).body(created);
	}
	
	@Operation(summary = "Update an existing customer", description = "Updates existing customer with the specified ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully updated customer",
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) },
			headers = { @Header(name = "location", description = "URL of the updated customer") }),
		@ApiResponse(responseCode = "404", description = "Customer not found"),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable long id, @RequestBody CustomerDTO customer)
	{
		CustomerDTO updated = new CustomerDTO(service.updateCustomer(id, customer));
		return ResponseEntity.ok(updated);
	}
	
	@Operation(summary = "Delete a customer", description = "Deletes a customer with the specified ID")
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable long id)
	{
		service.deleteCustomer(id);
	}
}
