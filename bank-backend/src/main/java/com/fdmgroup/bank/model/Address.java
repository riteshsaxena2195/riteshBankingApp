package com.fdmgroup.bank.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonPropertyOrder({ "id", "streetNumber", "city", "province", "postalCode" })
@JsonIgnoreProperties({ "id" })
@Entity
public class Address
{
	@Positive
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_GEN")
	@SequenceGenerator(name = "ADDRESS_GEN", sequenceName = "ADDRESS_SEQ")
	@Column(name = "ADDRESS_ID")
	private long addressId;
	
	@NotBlank
	@Size(min = 1, max = 50)
	@Column(name = "STREET_NUMBER", nullable = false, length = 50)
	private String streetNumber;
	
	@NotBlank
	@Size(min = 1, max = 50)
	@Column(name = "CITY", nullable = false, length = 50)
	private String city;
	
	@NotBlank
	@Size(min = 2, max = 2)
	@Column(name = "PROVINCE", nullable = false, length = 2)
	private String province;
	
	@NotBlank
	@Size(min = 5, max = 10)
	@Column(name = "POSTAL_CODE", nullable = false, length = 10)
	private String postalCode;
	
	/**
	 * Default constructor
	 */
	public Address()
	{
		super();
	}

	/**
	 * @param streetNumber
	 * @param city
	 * @param province
	 * @param postalCode
	 */
	public Address(@NotBlank @Size(min = 1, max = 50) String streetNumber,
		@NotBlank @Size(min = 1, max = 50) String city, @NotBlank @Size(min = 2, max = 2) String province,
		@NotBlank @Size(min = 5, max = 6) String postalCode)
	{
		super();
		this.streetNumber = streetNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}

	/**
	 * @param addressId
	 * @param streetNumber
	 * @param city
	 * @param province
	 * @param postalCode
	 */
	public Address(@Positive long addressId, @NotBlank @Size(min = 1, max = 50) String streetNumber,
		@NotBlank @Size(min = 1, max = 50) String city, @NotBlank @Size(min = 2, max = 2) String province,
		@NotBlank @Size(min = 5, max = 6) String postalCode)
	{
		super();
		this.addressId = addressId;
		this.streetNumber = streetNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}

	/**
	 * @return the addressId
	 */
	public long getId()
	{
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setId(long addressId)
	{
		this.addressId = addressId;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince()
	{
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province)
	{
		this.province = province;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	@Override
	public String toString()
	{
		return String.format("Address [addressId=%s, streetNumber=%s, city=%s, province=%s, postalCode=%s]",
			addressId, streetNumber, city, province, postalCode);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(addressId, streetNumber, city, province, postalCode);
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
		Address other = (Address) obj;
		return addressId == other.addressId && Objects.equals(city, other.city)
			&& Objects.equals(postalCode, other.postalCode) && Objects.equals(province, other.province)
			&& Objects.equals(streetNumber, other.streetNumber);
	}
}
