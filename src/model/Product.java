package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exception.InvalidArgumentsException;


public class Product {
	
	public static final int PRODUCT_NAME_MIN_LENGTH = 3;
	public static final String INVALID_PRODUCT_NAME = "Invalid ingredient name";
	public static final String INVALID_PRODUCT_PRICE = "Invalid ingredient price";
	
	private long id;
	private String name;
	private double price;
	private long categoryId;
	private HashSet<Size> sizes;
	private HashSet<Ingredient> ingredients;
	
	public Product(long id,String name, double price, long categoryId) throws InvalidArgumentsException {
		setId(id);
		setName(name);
		setPrice(price);
		setCategoryId(categoryId);
		setSizes(sizes);
		setIngredients(ingredients);
	}
	   
	public Product(String name, double price, HashSet<Size> sizes, long categoryId, HashSet<Ingredient> ingredients) throws InvalidArgumentsException {
		setName(name);
		setPrice(price);
		setCategoryId(categoryId);
		setSizes(sizes);
		setIngredients(ingredients);
	}
	
	public Product(long id,String name, double price, long categoryId, HashSet<Ingredient> ingredients) throws InvalidArgumentsException {
		this(id, name,price, categoryId);
		setIngredients(ingredients);
	}
	
	public Product(long id,String name, double price, long categoryId, HashSet<Size> sizes, HashSet<Ingredient> ingredients) throws InvalidArgumentsException {
		this(name,price, sizes, categoryId, ingredients);
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
		if(name != null && !(name.trim().equals("")) && name.trim().length() > PRODUCT_NAME_MIN_LENGTH) {
			this.name = name;
		} 
		else {
			throw new InvalidArgumentsException(INVALID_PRODUCT_NAME);
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
			throw new InvalidArgumentsException(INVALID_PRODUCT_PRICE);
		}
	}
	
	

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public Set<Size> getSizes() {
		return Collections.unmodifiableSet(sizes);
	}

	public void setSizes(HashSet<Size> sizes) {
		if(sizes != null) {
			this.sizes = sizes;
		}
	}
	
	public Set<Ingredient> getIngredients() {
		return Collections.unmodifiableSet(ingredients);
	}

	public void setIngredients(HashSet<Ingredient> ingredients) {
		if(ingredients != null) {
			this.ingredients = ingredients;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
}