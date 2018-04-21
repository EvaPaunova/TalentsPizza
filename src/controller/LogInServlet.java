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
import model.User;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//get parameters from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		
		//check in db if user exists
		try {
			user = UserManager.getInstance().logIn(username, password);
		} catch (SQLException e) {
			e.getMessage();
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
		
		//if user exists add attributes to session
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("logged.html");
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} 
	}
}