package controller.manager;

import java.sql.SQLException;
import java.util.List;
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

	public void addNewProduct(Product product) throws SQLException {
		productDao.addNewProduct(product);
	}
	
	public void deleteProduct(Product product) throws SQLException{
		productDao.deleteProduct(product);
	}
	
	public void updateProduct(Product product) throws SQLException{
		productDao.updateProduct(product);
	}
	
	public List<Product> getListOfAllProduct() throws SQLException{
		List<Product> products = productDao.getAllProducts();
		return products;
		
	}
	

}
