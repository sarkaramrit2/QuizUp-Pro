package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.RegistrationDaoI;
import com.dao.RegistrationDaoImpl;
import com.entity.User;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationDaoI regdao;

	public void setRegdao(RegistrationDaoI regdao,Model model) {
		this.regdao = regdao;
		
	}
	
	

	@RequestMapping(value = "reg.htm", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("RegisterUser",new User());
		return "Register";
	}
	
	
	@RequestMapping(value = "adduser.htm", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("RegisterUser")User user, Model model) {
		regdao.addUser(regdao.maxKey("User", "user_id")+1,user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getProfile_pic(),user.getEmail(), user.getPassword());
		return "Register";
	}

}
