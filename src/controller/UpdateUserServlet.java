package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.UserManager;
import exception.InvalidArgumentsException;
import model.User;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public UpdateUserServlet() {
        super();
    }

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
				UserManager.getInstance().updateUser(user);
				response.sendRedirect("logged.html");
				
				
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} 
	}

}
