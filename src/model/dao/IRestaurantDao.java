package model.dao;

import java.sql.SQLException;
import java.util.List;

import exception.InvalidArgumentsException;
import model.Restaurant;

public interface IRestaurantDao {

	public List<Restaurant> getAllRestaurants() throws SQLException, InvalidArgumentsException;
	
	public void deleteRestaurant(Restaurant restaurant) throws SQLException;
	
	public void updateRestaurant(Restaurant restaurant) throws SQLException;
	
	public void addNewRestaurant(Restaurant restaurant) throws SQLException;
	
}
