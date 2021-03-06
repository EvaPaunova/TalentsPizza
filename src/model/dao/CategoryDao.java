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
import model.Category;

public class CategoryDao implements ICategoryDao{
	
	private static CategoryDao instance;
	private Connection connection;
	
	
	private CategoryDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static CategoryDao getInstance() {
		if(instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}

	@Override
	public List<Category> getAllCategories() throws SQLException, InvalidArgumentsException {
		String sqlSelectAllCategories = "SELECT * FROM categories;";
		List<Category> categories = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllCategories)){
			ResultSet set = ps.executeQuery();
			
			while (set.next()) {
				long id = set.getInt("category_id");
				String name = set.getString("name");
				categories.add(new Category(id,name));
			}
		}
		return Collections.unmodifiableList(categories);
	}

	@Override
	public void deleteCategory(Category category) throws SQLException {
		String sqlDeleteCategory = "DELETE FROM categories WHERE category_id = ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlDeleteCategory)){
			ps.setLong(1, category.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void updateCategory(Category category) throws SQLException {
		String sqlUpdateCategory  = "UPDATE ingredients SET name = ?, WHERE category_id = ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlUpdateCategory)){
			ps.setString(1, category.getName());
			ps.setLong(2, category.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void addNewtegory(Category category) throws SQLException {
		String sqlInsertCategory = "INSERT INTO ingredients (name) VALUES(?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sqlInsertCategory)){
			ps.setString(1, category.getName());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			category.setId(rs.getLong("category_id"));
		}
	}

}
