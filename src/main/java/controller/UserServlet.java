package controller;

import java.io.IOException;


import model.UserBean;

import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();

	public UserServlet() {
		super();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at:").append(request.getContextPath());
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
 
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		
		UserBean user=new UserBean();
		
		user.setFirst_name(firstname);
		user.setLast_name(lastname);
		user.setUsername(username);
		user.setPassword(pass);
		
		int result = userDao.registerUser(user);
		
		if(result == 1) {
			session.setAttribute("firstName",user.getFirst_name());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("registrationsucess.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			session.setAttribute("error", "Username already exist");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("registration.jsp");
			requestDispatcher.forward(request, response);

		}

		
	}

}
