package controller.manager;

import java.util.List;
import java.sql.SQLException;
import model.Ingredient;
import model.dao.IIngredientDao;
import model.dao.IngredientDao;

public class IngredientManager {
	
	private static IngredientManager instance;
	private IIngredientDao ingredientDao;
	
	private IngredientManager() {
		this.ingredientDao = IngredientDao.getInstance();
	}

	public static IngredientManager getInstance() {
		if (instance == null) {
			instance = new IngredientManager();
		}
		return instance;
	}

	public void createNewIngredient() throws SQLException{
		
	}
	
	public void deleteIngredient(Ingredient ingredient) throws SQLException{
		
	}
	
	public void updateIngredient(Ingredient ingredient) throws SQLException{
		
	}
	
	public List<Ingredient> getListOfAllIngredients() throws SQLException{
		return null;
		
	}
	

}
