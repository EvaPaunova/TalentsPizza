package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.Product;
import model.User;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/delete")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product_id = request.getParameter("product_id");
		User user = (User) request.getAttribute("user");
		Map<Product, Integer> cart = user.getCart();
		
		// if current quantity = 1 delete product else decrease quantity with 1
		
		for (Entry<Product, Integer> e : cart.entrySet()) {
			if(e.getKey().getId() == Integer.valueOf(product_id)) {
				if(e.getValue() > 1) {
					cart.put(e.getKey(), e.getValue()-1);
				}
				else {
					cart.remove(e.getKey());
				}
			}
		}
		
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
	}

}
