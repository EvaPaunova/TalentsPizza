package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DBManager;
import exception.InvalidArgumentsException;
import model.Ingredient;
import model.Product;

public class ProductDao {
	private static ProductDao instance;
	private Connection connection;
	
	
	private ProductDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	public List<Product> getAllProducts() throws SQLException, InvalidArgumentsException {
		String sqlSelectAllProducts = "SELECT product_id,name,price,category_id FROM products;";
		List<Product> products = new ArrayList<>();
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllProducts,Statement.RETURN_GENERATED_KEYS)){
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				long product_id =  set.getLong("product_id");
				String name = set.getString("name");
				Double price = set.getDouble("price");
				long category_id = set.getLong("category_id");
				
				Product product = new Product(product_id, name, price, category_id);
				products.add(product);
			}
		}
		
		return products;
	}
	
	public void addNewProduct(Product product) throws SQLException {
		connection.setAutoCommit(false);
		String sqlInsertProduct = "INSERT INTO products (name, category_id, price) VALUES(?,?,?)";
		try {
			try(PreparedStatement ps = connection.prepareStatement(sqlInsertProduct,Statement.RETURN_GENERATED_KEYS)){
				ps.setString(1, product.getName());
				ps.setLong(2,product.getCategoryId());
				ps.setDouble(3, product.getPrice());
				ps.executeUpdate();
				try(ResultSet rs = ps.getGeneratedKeys()){
					if(rs.next()) {
						product.setId(rs.getLong(1));
					}
				}
			}
			addIngredients(product);
			connection.commit();
		}
		catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		finally {
			connection.setAutoCommit(true);
		}
	}
	
	public void addIngredients(Product product) {
		ArrayList<Ingredient> ingredients = new ArrayList<>(product.getIngredients());
		for(int i = 0; i < ingredients.size(); i++) {
			String sql = "INSERT INTO products_has_ingredients (product_id, ingredient_id) \\nVALUES(?,?)";
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setLong(1, product.getId());
				ps.setLong(1, ingredients.get(i).getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
}
	
	public void deleteProduct(Product product) throws SQLException{
		String sqlDeleteProduct = "DELETE FROM products \nWHERE product_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sqlDeleteProduct)){
			ps.setLong(1, product.getId());
			ps.executeUpdate();
		}
		
	}
	
	public void updateProduct(Product product) throws SQLException {
		String sqlUpdateProduct  = "UPDATE products SET ... WHERE product_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sqlUpdateProduct)){
			ps.setString(1, product.getName());
			ps.setLong(2, product.getCategoryId());
			ps.setDouble(3, product.getPrice());
			System.out.println();
			ps.setLong(7, product.getId());
			ps.executeUpdate();
		} 
	}
	public Product getProductById(int id) throws SQLException, InvalidArgumentsException {
		Product product = null;
		String sqlSelectProduct = "SELECT name,price,category_id FROM products WHERE product_id = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectProduct)){
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				double price = result.getDouble("price");
				long category_id = result.getLong("category_id");
				product = new Product(id, name, price, category_id);
			}
		}
			
		return product;
	}	
}
