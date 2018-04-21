package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DBManager;
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
	
	public List<Product> getAllProducts() throws SQLException {
		String sqlSelectAllProducts = "SELECT ... FROM products;";
		List<Product> products = new ArrayList<>();
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllProducts,Statement.RETURN_GENERATED_KEYS)){
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				long product_id = set.getLong("product_id");
				String name = set.getString("name");
				Double price = set.getDouble("price");
			//	Product product = new Product(name, price, sizes, categoryId, ingredients);
			//	product.setId(product_id);
			//	products.add(product);
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
	
}
