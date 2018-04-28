package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
					if(isExist(Integer.parseInt(id), cart)) {
						for (Entry<Product, Integer> entry : cart.entrySet()) {
							if(entry.getKey().equals(ProductDao.getInstance().getProductById(Integer.parseInt(id)))) {
								int quantity = entry.getValue();
								cart.put(ProductDao.getInstance().getProductById(Integer.parseInt(id)), quantity+1);
							}
						}
					}
					else {
						cart.put(ProductDao.getInstance().getProductById(Integer.parseInt(id)), 1);
						user.addProductToShoppingCart(ProductDao.getInstance().getProductById(Integer.parseInt(id)), 1);
					}
				} catch (NumberFormatException | SQLException | InvalidArgumentsException e) {
					System.out.println(e.getMessage());
				}
			}	
			request.getSession().setAttribute("cart", cart);
			request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		}
		else if(action.equals("delete")) {
			cart.putAll((Map<Product, Integer>)request.getSession().getAttribute("cart"));
			if(isExist(Integer.parseInt(id), cart)) {
				for (Entry<Product, Integer> entry : cart.entrySet()) {
					try {
						if(entry.getKey().equals(ProductDao.getInstance().getProductById(Integer.parseInt(id)))) {
							int quantity = entry.getValue();
							if(quantity == 1) {
								cart.remove(ProductDao.getInstance().getProductById(Integer.parseInt(id)));
							}
							else {
								cart.put(ProductDao.getInstance().getProductById(Integer.parseInt(id)), quantity-1);
							}
						}
					} catch (NumberFormatException | SQLException | InvalidArgumentsException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			request.getSession().setAttribute("cart", cart);
			request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		}
		
	}
	
	private boolean isExist(int id,Map<Product, Integer> cart) {
		for (Entry<Product, Integer> e : cart.entrySet()) {
			if(e.getKey().getId() == id) {
				return true;
			}
		}
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
