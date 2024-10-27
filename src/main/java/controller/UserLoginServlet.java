package controller;

import java.io.IOException;


import model.UserBean;
import service.UserService;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = new UserDao();
	UserService usrService = new UserService();

	public UserLoginServlet() {
		super();
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
 
		
	
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		
		UserBean user=new UserBean();

		user.setUsername(username);
		user.setPassword(pass);
		
		UserBean result = usrService.validatelogin(user);
		
		if(result.getFirst_name() == null) {
			session.setAttribute("error", "Invalid Username/Password");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
			
		}
		else {
			session.setAttribute("firstName",user.getFirst_name());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginsuccess.jsp");
			requestDispatcher.forward(request, response);

		}

		
	}

}
