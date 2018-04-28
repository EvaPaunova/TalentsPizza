package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.InvalidArgumentsException;
import model.Product;
import model.User;
import model.dao.ProductDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/shoppingcart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		Map<Product, Integer> cart = new HashMap<>();
		
		if(action.equals("addToCart")) {
			if (request.getSession().getAttribute("cart") == null) {
				try {
					user.addProductToShoppingCart(ProductDao.getInstance().getProductById(Integer.parseInt(id)), 1);
					cart = user.getCart();
				} catch (NumberFormatException | SQLException | InvalidArgumentsException e) {
					System.out.println(e.getMessage());
				}
				
			}
			else {
				cart.putAll((Map<Product, Integer>)request.getSession().getAttribute("cart"));
				try {
					cart.put(ProductDao.getInstance().getProductById(Integer.parseInt(id)), 1);
					user.addProductToShoppingCart(ProductDao.getInstance().getProductById(Integer.parseInt(id)), 1);
					//todo if product exist in cart quantity++
				} catch (NumberFormatException | SQLException | InvalidArgumentsException e) {
					System.out.println(e.getMessage());
				}
			}	
			request.getSession().setAttribute("cart", cart);
			request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
