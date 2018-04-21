package controller.manager;

import java.sql.SQLException;
import java.util.List;
import model.Ingredient;
import model.Product;
import model.dao.ProductDao;

public class ProductManager {
	
	private static ProductManager instance;
	private ProductDao productDao;
	
	private ProductManager() {
		this.productDao = ProductDao.getInstance();
	}

	public static ProductManager getInstance() {
		if (instance == null) {
			instance = new ProductManager();
		}
		return instance;
	}

	public void addNewProduct() throws SQLException {
		
	}
	
	public void deleteProduct(Product product) throws SQLException{
		
	}
	
	public void updateProduct(Product product) throws SQLException{
		
	}
	
	public List<Product> getListOfAllProduct() throws SQLException{
		return null;
		
	}
	

}
