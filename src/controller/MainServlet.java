package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import exception.InvalidArgumentsException;
import model.Ingredient;
import model.Product;
import model.dao.IngredientDao;
import model.dao.ProductDao;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("")
public class MainServlet extends HttpServlet {
	//index servlet
	private static final long serialVersionUID = 1L;
       
    
    public MainServlet() {
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//load products if attribute is empty
		ServletContext application = getServletConfig().getServletContext();
		if (application.getAttribute("products") == null || application.getAttribute("ingredients") == null) {
			List<Product> products = new ArrayList<>();
			List<Ingredient> ingredients = new ArrayList<>();
			try {
				products = ProductDao.getInstance().getAllProducts();
				System.out.println(products);
				ingredients = IngredientDao.getInstance().getAllIngredients();
			} catch (SQLException |InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				request.setAttribute("exception", e);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			application.setAttribute("products", products);
			application.setAttribute("ingredients", ingredients);
		}
		//check if user is logged and redirect
		boolean logged = false;
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			logged = true;
		}
		if (logged) {
			request.getRequestDispatcher("logged.html").forward(request, response);
		} else {
			request.getRequestDispatcher("html.html").forward(request, response);
		}
	}


}
