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
				int product_id =  set.getInt("product_id");
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
		String sqlInsertProduct = "INSERT INTO products () VALUES(?,?,?)";
		try(PreparedStatement ps = connection.prepareStatement(sqlInsertProduct,Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, product.getName());
			ps.setLong(2,product.getCategoryId());
			ps.setDouble(3, product.getPrice());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					product.setId(rs.getLong(1));
				}
				System.out.println("New user added!");
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
