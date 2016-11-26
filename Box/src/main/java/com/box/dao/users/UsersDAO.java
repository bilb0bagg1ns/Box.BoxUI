package com.box.dao.users;

import com.box.model.User;

public interface UsersDAO {

	public void save (User user);
	public User findByUserNamePassword(String userName, String password);
}
