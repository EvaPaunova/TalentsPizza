package controller.manager;

import java.util.List;
import exception.InvalidArgumentsException;
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

	public void addNewIngredient(Ingredient ingredient) throws SQLException{
		ingredientDao.addNewIngredient(ingredient);
	}
	
	public void deleteIngredient(Ingredient ingredient) throws SQLException{
		ingredientDao.deleteIngredient(ingredient);
	}
	
	public void updateIngredient(Ingredient ingredient) throws SQLException{
		ingredientDao.updateIngredient(ingredient);
	}
	
	public List<Ingredient> getListOfAllIngredients() throws SQLException, InvalidArgumentsException{
		List<Ingredient> ingredients = ingredientDao.getAllIngredients();
		
		return ingredients;		
	}
	

}
