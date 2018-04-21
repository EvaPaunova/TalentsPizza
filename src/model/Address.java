package model;

import exception.InvalidArgumentsException;

public class Address {
	
	public static final String INVALID_LOCATION = "Invalid location";
	
	private long id;
	private String location;
	private long userId;
	
	public Address(String location, long userId) throws InvalidArgumentsException {
		setLocation(location);
		setUserId(userId);
	}
	
	public Address(long id, String location, long userId) throws InvalidArgumentsException {
		this(location,userId);
		setId(userId);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) throws InvalidArgumentsException {
		if(location != null && !(location.trim().equals(""))) {
			this.location = location;
		} 
		else {
			throw new InvalidArgumentsException(INVALID_LOCATION);
		}
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
