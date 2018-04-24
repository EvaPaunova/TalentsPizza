package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DBManager;
import exception.InvalidArgumentsException;
import model.Restaurant;

public class RestaurantDao implements IRestaurantDao{
	
	private static RestaurantDao instance;
	private Connection connection;
	
	
	private RestaurantDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static RestaurantDao getInstance() {
		if(instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}

	@Override
	public List<Restaurant> getAllRestaurants() throws SQLException, InvalidArgumentsException {
		String sqlSelectAllRestaurants = "SELECT * FROM addresses;";
		List<Restaurant> restaurants = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllRestaurants)){
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				String name = set.getString("name");
				String phoneNumber = set.getString("phone_number");
				String city = set.getString("city");
				String address = set.getString("address");
				restaurants.add(new Restaurant(name, phoneNumber, city, address));
			}
		}
		return restaurants;
	}

	@Override
	public void deleteRestaurant(Restaurant restaurant) throws SQLException {
		String sqlDeleteRestaurant = "DELETE FROM restaurants WHERE restaurant_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sqlDeleteRestaurant)){
			ps.setLong(1, restaurant.getId());
			ps.executeUpdate();
		} 
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) throws SQLException {
		String sqlUpdateAddress  = "UPDATE restaurants SET name = ?, phone_number = ?, city = ?, address = ? WHERE restaurant_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sqlUpdateAddress)){
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getPhoneNumber());
			ps.setString(3, restaurant.getCity());
			ps.setString(4, restaurant.getAddress());
			ps.setLong(5, restaurant.getId());
			ps.executeUpdate();
		} 
	}

	@Override
	public void addNewRestaurant(Restaurant restaurant) throws SQLException {
		String sqlInsertRestaurant = "INSERT INTO restaurants (name, phone_number, city, address) VALUES(?,?,?,?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlInsertRestaurant)){
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getPhoneNumber());
			ps.setString(3, restaurant.getCity());
			ps.setString(4, restaurant.getAddress());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			restaurant.setId(rs.getLong("restaurant_id"));
		}
	}

}
