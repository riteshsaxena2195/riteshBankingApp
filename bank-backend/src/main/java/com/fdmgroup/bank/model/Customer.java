package com.fdmgroup.bank.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "customerType"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Person.class, name = CustomerType.PERSON_STRING),
	@JsonSubTypes.Type(value = Company.class, name = CustomerType.COMPANY_STRING)
})
@JsonPropertyOrder({
	"customerId",
	"name",
	"customerType",
	"address",
	"accounts"
})
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "customerId"
)
@SequenceGenerator(
	name = "CUSTOMER_GEN",
	sequenceName = "CUSTOMER_SEQ",
	initialValue = 1001
)
@DiscriminatorColumn(
	name = "CUSTOMER_TYPE",
	discriminatorType = DiscriminatorType.STRING,
	length = 20
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Customer
{
	@Positive
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_GEN")
	@Column(name = "CUSTOMER_ID")
	private long customerId;
	
	@NotBlank
	@Size(min = 1, max = 50)
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_ID", referencedColumnName = "ADDRESS_ID", nullable = false)
	private Address address;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	//@JsonManagedReference
	//@JsonIgnoreProperties("customerId")
	private List<Account> accounts;

	/**
	 * Default constructor for JPA
	 */
	protected Customer()
	{
		super();
	}
	
	/**
	 * @param name
	 * @param address
	 */
	protected Customer(String name, Address address)
	{
		super();
		this.name = name;
		this.address = address;
	}

	/**
	 * @param customerId
	 * @param name
	 * @param address
	 */
	protected Customer(long customerId, String name, Address address)
	{
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId()
	{
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId)
	{
		this.customerId = customerId;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address)
	{
		this.address = address;
	}

	public List<Account> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}

	@Override
	public String toString()
	{
		return String.format("Customer [customerId=%s, name=%s, address=%s, accounts=%s]",
				customerId, name, address, accounts.stream().map(Account::getAccountId).toList());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(customerId, name, address);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && customerId == other.customerId
			&& Objects.equals(name, other.name);
	}
}
