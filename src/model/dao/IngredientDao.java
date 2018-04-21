package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.DBManager;
import exception.InvalidArgumentsException;
import model.Ingredient;

public class IngredientDao implements IIngredientDao{
	
	private static IngredientDao instance;
	private Connection connection;
	
	
	private IngredientDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static IngredientDao getInstance() {
		if(instance == null) {
			instance = new IngredientDao();
		}
		return instance;
	}

	@Override
	public List<Ingredient> getAllIngredients() throws SQLException, InvalidArgumentsException {
		
		String sqlSelectAllIngredients = "SELECT * FROM ingredients;";
		List<Ingredient> ingredients = new ArrayList<>();
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllIngredients)){
			ResultSet set = ps.executeQuery();
			
			while (set.next()) {
				long id = set.getInt("ingredient_id");
				String name = set.getString("name");
				double price = set.getDouble("price");
				ingredients.add(new Ingredient(id,name,price));
		}
		}
		return Collections.unmodifiableList(ingredients);
	}

	@Override
	public void deleteIngredient(Ingredient ingredient) throws SQLException{
		
		String sqlDeleteIngredient = "DELETE FROM ingredients WHERE ingredient_id = ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlDeleteIngredient)){
			ps.setLong(1, ingredient.getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void updateIngredient(Ingredient ingredient) throws SQLException{
		
		String sqlUpdateIngredient  = "UPDATE ingredients SET name = ?, price = ? WHERE ingredient_id = ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlUpdateIngredient)){
			ps.setString(1, ingredient.getName());
			ps.setDouble(2, ingredient.getPrice());
			ps.setLong(3, ingredient.getId());
			ps.executeUpdate();
		}

		
	}

	@Override
	public void addNewIngredient(Ingredient ingredient) throws SQLException{
		
		String sqlInsertIngredient = "INSERT INTO ingredients (name, price) VALUES(?,?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlInsertIngredient)){
			ps.setString(1, ingredient.getName());
			ps.setDouble(2, ingredient.getPrice());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			ingredient.setId(rs.getLong("ingredient_id"));
		}

	}

}