package service;

import dao.UserDao;
import model.UserBean;

public class UserService {
	
	UserDao usrDao=new UserDao();
	
	public UserBean validatelogin(UserBean user) {
		if(user.getUsername().isBlank() || user.getPassword().isBlank())
		return user;
		
		
		UserBean currentUser = usrDao.getUserDetails(user);
		
		return currentUser;
		
	}

	

}
