package com.fdmgroup.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fdmgroup.bank.model.AccountDTO;
import com.fdmgroup.bank.model.AccountType;
import com.fdmgroup.bank.model.Customer;
import com.fdmgroup.bank.model.CustomerDTO;
import com.fdmgroup.bank.model.CustomerType;
import com.fdmgroup.bank.service.AccountService;
import com.fdmgroup.bank.service.CustomerService;
import com.github.javafaker.Faker;

@SpringBootApplication
public class BankApplication implements CommandLineRunner, WebMvcConfigurer
{
	private ApplicationContext context;
	
	public static void main(String[] args)
	{
		SpringApplication.run(BankApplication.class, args);
	}
	
	public BankApplication(ApplicationContext context)
	{
		super();
		this.context = context;
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		initializeDB();
	}

    @Bean
    WebClient.Builder webClientBuilder()
	{
		return WebClient.builder();
	}
    
    @Bean
    WebClient addressWebClient(WebClient.Builder builder)
    {
    	return builder.baseUrl("https://geocoder.ca")
    			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			.build();
    }
    
    public void initializeDB()
    {
    	CustomerService customerService = context.getBean(CustomerService.class);
		AccountService accountService = context.getBean(AccountService.class);
		Faker faker = new Faker(new java.util.Random(1234));
		
		for (int i = 0; i < 20; i++)
		{
			CustomerDTO customerDTO = new CustomerDTO();
			CustomerType type = faker.options().option(CustomerType.class);
			if (type == CustomerType.COMPANY) {
				customerDTO.setName(faker.company().name());
				customerDTO.setCustomerType(CustomerType.COMPANY_STRING);
			}
			else {
				customerDTO.setName(faker.name().fullName());
				customerDTO.setCustomerType(CustomerType.PERSON_STRING);
			}
			customerDTO.setStreetNumber(faker.address().streetAddress());
			customerDTO.setPostalCode(faker.options().option("78759", "M5V 3L9", "07008"));
			if (customerDTO.getPostalCode().equals("07008")) {
				customerDTO.setCity("Newark");
				customerDTO.setProvince("NJ");
			} else if (customerDTO.getPostalCode().equals("M5V 3L9")) {
				customerDTO.setCity("Toronto");
				customerDTO.setProvince("ON");
			} else {
				customerDTO.setCity("Austin");
				customerDTO.setProvince("TX");
			}
			
			Customer customer = customerService.createCustomer(customerDTO);
			
			for (int j = 0; j < faker.number().numberBetween(1, 3); j++)
			{
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setAccountType(faker.options().option(AccountType.CHECKING_STRING, AccountType.SAVINGS_STRING));
				accountDTO.setBalance(faker.number().randomDouble(2, 100, 10000));
				if (accountDTO.getAccountType() == AccountType.CHECKING_STRING) {
					accountDTO.setNextCheckNumber(faker.number().numberBetween(1, 25));
				} else if (accountDTO.getAccountType() == AccountType.SAVINGS_STRING) {
					accountDTO.setInterestRate(faker.number().randomDouble(2, 2, 15) / 100d);
				}
				accountDTO.setCustomerId(customer.getCustomerId());
				accountService.createAccount(accountDTO);
			}
		}
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**")
				.allowedOrigins("*") // Tüm origin'lere izin ver (Cloud Run için)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(false); // allowedOrigins("*") kullandığında false olmalı
	}
}