package model.dao;

import java.sql.Connection;
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
	
	public List<Product> getAllProducts() {
		return null;
		
	}
}
