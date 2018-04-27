package controller;

import java.io.IOException;
import java.sql.SQLException;
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
		User user = (User) request.getSession().getAttribute("user");
		Map<Product, Integer> cart = null;
		if (request.getSession().getAttribute("cart") == null) {
			cart = user.getCart();
		}
		else {
			cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		}
		
		String id = request.getParameter("productId");
		
		try {
			Product p = ProductDao.getInstance().getProductById(Integer.parseInt(id));
			user.addProductToShoppingCart(p, 1);
			System.out.println(user.getCart());
		} catch (NumberFormatException | SQLException | InvalidArgumentsException e) {
			System.out.println(e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Map<Product, Integer> cart =null;
		if (request.getSession().getAttribute("cart") == null) {
			cart = user.getCart();
		}
		else {
			cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");

		}
		
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		
	}

}
