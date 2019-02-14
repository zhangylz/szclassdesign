package com.ylzh.helloworld.service;

import com.ylzh.helloworld.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
    /**
     * 更新最后登录时间
     * @param user
     */
    void updateLastLoginTime(User user);
    User selectByUsername(String userName);
}
