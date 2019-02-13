package com.ylzh.helloworld.service;

import com.ylzh.helloworld.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);

}
