package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.manager.UserManager;
import exception.InvalidArgumentsException;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirm password");
		String phoneNumber = request.getParameter("phone number");

		User user = null;
		
		if (password.equals(confirmpassword)) {
			try {
				user = new User(username, firstName, lastName, phoneNumber, password, email);
			} catch (InvalidArgumentsException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("error.jsp").forward(request, response);
				System.out.println(e.getMessage());
			}
		}
		
		if (user != null) {
			try {
				if(UserManager.getInstance().register(user)) {
					response.sendRedirect("html.html");
				}
				else {
					response.sendRedirect("error.jsp");
				}	
				
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} 
	}
			
	

}