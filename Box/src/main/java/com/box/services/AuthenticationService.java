package com.box.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.box.dao.users.UsersDAO;
import com.box.dao.users.UsersDAOImpl;
import com.box.model.User;

public class AuthenticationService {

	@Autowired
	private UsersDAO usersDAO = new UsersDAOImpl();
	public boolean isAuthenticated (User user){
		boolean isAuthenticated = false;
		
		User retrievedUser = usersDAO.findByUserNamePassword(user.getUserName(), user.getPassword());
		
		if ((retrievedUser != null) && (retrievedUser.getUserName().equals(user.getUserName())) && (retrievedUser.getPassword().equals(user.getPassword()))){
			isAuthenticated = true;			
		}
		return isAuthenticated;
	}
}
