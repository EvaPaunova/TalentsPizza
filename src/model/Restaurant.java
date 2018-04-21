package model;

import exception.InvalidArgumentsException;

public class Restaurant {
	
	private static final int MIN_NAME_LENGTH = 3;
	private static final int MIN_CITY_LENGTH = 3;
	private static final int MIN_LASTNAME_LENGTH = 3;
	public static final int ADDRESS_MIN_LENGTH = 10;
	public static final int ADDRESS_MAX_LENGTH = 40;
	public static final int PHONE_MIN_LENGTH = 8;
	public static final String INVALID_NAME = "Invalid name";
	public static final String INVALID_PHONE = "Invalid phone number";
	public static final String INVALID_ADDRESS = "Invalid address";
	public static final String INVALID_CITY = "Invalid city";

	
	private long id;
	private String name;
	private String phoneNumber;
	private String city;
	private String address;
	
	public Restaurant(String name, String phoneNumber, String city, String address) throws InvalidArgumentsException {
		setName(name);
		setPhoneNumber(phoneNumber);
		setCity(city);
		setAddress(address);
	}
	
	public Restaurant(long id, String name, String phoneNumber, String city, String address) throws InvalidArgumentsException {
		this(name,phoneNumber,city,address);
		setId(id);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws InvalidArgumentsException {
		if (name.trim().length() >= MIN_NAME_LENGTH) {
			this.name = name;
		} else {
			throw new InvalidArgumentsException(INVALID_NAME);
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) throws InvalidArgumentsException {
		if ((phoneNumber.matches("[0-9]+")) && (!phoneNumber.isEmpty()) && (!phoneNumber.equals(""))
				&& phoneNumber.trim().length() >= PHONE_MIN_LENGTH) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidArgumentsException(INVALID_PHONE);
		}
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) throws InvalidArgumentsException {
		if (city.trim().length() >= MIN_CITY_LENGTH) {
			this.city = city;
		} else {
			throw new InvalidArgumentsException(INVALID_CITY);
		}
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) throws InvalidArgumentsException {
		if(address != null && !(address.trim().equals("")) && address.trim().length() > ADDRESS_MIN_LENGTH && 
				address.trim().length() < ADDRESS_MAX_LENGTH) {
			this.address = address;
		} 
		else {
			throw new InvalidArgumentsException(INVALID_ADDRESS);
		}
	}
	
	

}
