package com.fdmgroup.bank.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fdmgroup.bank.model.Address;

@Service
public class AddressClientGeocoder implements IAddressClient
{
	private WebClient addressWebClient;
	
	public AddressClientGeocoder(WebClient addressWebClient)
	{
		super();
		this.addressWebClient = addressWebClient;
	}
	
	private Address mapJsonToAddress(JsonNode json)
	{
		JsonNode addressNode = json.get("standard");
		Address address = new Address();
		address.setStreetNumber(addressNode.get("stnumber").asText()+" "+addressNode.get("staddress").asText());
		address.setCity(addressNode.get("city").asText());
		address.setProvince(addressNode.get("prov").asText());
		if (addressNode.has("postal")) {
			address.setPostalCode(addressNode.get("postal").asText());
		} else {
			address.setPostalCode(json.get("postal").asText());
		}
		return address;
	}
	
	@Override
	public Address getAddress(Address address)
	{
		if (address.getStreetNumber() != null && !address.getStreetNumber().isBlank() &&
			address.getCity() != null && !address.getCity().isBlank() &&
			address.getProvince() != null && !address.getProvince().isBlank() &&
			address.getPostalCode() != null && !address.getPostalCode().isBlank()) {

			return address;
		}
		try {
			String lookup = String.join("+", address.getStreetNumber(), address.getCity(), address.getProvince(),
				address.getPostalCode()).replace(' ', '+');
			Address addr = addressWebClient
				.get()
				.uri(String.format("/?locate=%s&json=1", lookup.replace(' ', '+')))
				.retrieve()
				.bodyToMono(JsonNode.class)
				.map(this::mapJsonToAddress)
				.block();
			addr.setId(address.getId());
			return addr;
		} catch (Exception e) {
			return new Address(
				address.getId(),
				address.getStreetNumber() == null || address.getStreetNumber().isBlank() ? "Unknown Street" : address.getStreetNumber(),
				address.getCity() == null || address.getCity().isBlank() ? "Unknown City" : address.getCity(),
				address.getProvince() == null || address.getProvince().isBlank() ? "NA" : address.getProvince(),
				address.getPostalCode() == null || address.getPostalCode().isBlank() ? "00000" : address.getPostalCode()
			);
		}
	}
}
