package com.ylzh.helloworld.service;

import java.util.Arrays;
import java.util.HashSet;

import com.ylzh.helloworld.model.Role;
import com.ylzh.helloworld.model.User;
import com.ylzh.helloworld.repository.RoleRepository;
import com.ylzh.helloworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Qualifier("userRepository")
	@Autowired
	private UserRepository userRepository;
	@Qualifier("roleRepository")
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setCreateTime();
		user.updateLastLoginTime();
		user.updateUpdateTime();
		userRepository.save(user);
	}
	
    @Override
    public void updateLastLoginTime(User user) {
    	user.updateLastLoginTime();
    	userRepository.save(user);
    }
    
    @Override
    public User selectByUsername(String userName) {
    	return userRepository.findByLastName(userName);
    }

}
