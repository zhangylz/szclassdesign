package com.ylzh.helloworld.controller;

import javax.validation.Valid;



import com.ylzh.helloworld.model.User;
import com.ylzh.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ylzh.helloworld.vo.base.ResponseVo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang.StringUtils;
import com.ylzh.helloworld.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	/*提交登录*/
	@PostMapping("/login")
	@ResponseBody
	public ResponseVo login(HttpServletRequest request, String username, String password, String verification,
							@RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe){
		//判断验证码
		String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
			//验证码通过
		} else {
			return ResultUtil.error("验证码错误！");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			token.setRememberMe(1 == rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
		} catch (LockedAccountException e) {
			token.clear();
			return ResultUtil.error("用户已经被锁定不能登录，请联系管理员！");
		} catch (AuthenticationException e) {
			token.clear();
			return ResultUtil.error("用户名或者密码错误！");
		}
		//更新最后登录时间
		userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
		return ResultUtil.success("登录成功！");
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user","*There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully" +
					"<i class=\"em em-white_check_mark\"></i>\n");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}

		return modelAndView;
	}
}
