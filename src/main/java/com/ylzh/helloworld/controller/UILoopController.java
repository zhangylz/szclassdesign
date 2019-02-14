package com.ylzh.helloworld.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ylzh.helloworld.model.TableUser;
import com.ylzh.helloworld.model.User;
import com.ylzh.helloworld.service.UserService;


@Controller
@RequestMapping(value="/admin")
public class UILoopController {
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.queries.view-users}")
    private String query;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value="database", method = RequestMethod.GET)
	public ModelAndView database() throws SQLException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/database");
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();

		ArrayList<TableUser> list = new ArrayList<TableUser>();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
				TableUser user = new TableUser();
				user.setId(rs.getString("user_id"));
				user.setActive(rs.getString("active"));
				user.setEmail(rs.getString("email"));
				user.setLastName(rs.getString("last_name"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				list.add(user);
		}
		modelAndView.addObject("users",list);

		return modelAndView;
	}
}
