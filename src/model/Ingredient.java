package model;

import exception.InvalidArgumentsException;

public class Ingredient {
	
	public static final int INGREDIENT_NAME_MIN_LENGTH = 3;
	public static final String INVALID_INGREDIENT_NAME = "Invalid ingredient name";
	public static final String INVALID_INGREDIENT_PRICE = "Invalid ingredient price";

	private long id;
	private String name;
	private double price;
	
	public Ingredient(String name, double price) throws InvalidArgumentsException {
		setName(name);
		setPrice(price);
	}

	public Ingredient(long id, String name, double price) throws InvalidArgumentsException {
		this(name, price);
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
		if(name != null && !(name.trim().equals("")) && name.trim().length() > INGREDIENT_NAME_MIN_LENGTH) {
			this.name = name;
		} 
		else {
			throw new InvalidArgumentsException(INVALID_INGREDIENT_NAME);
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws InvalidArgumentsException {
		if(price > 0) {
			this.price = price;
		}
		else {
			throw new InvalidArgumentsException(INVALID_INGREDIENT_PRICE);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
}
