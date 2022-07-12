package com.dev.FinalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.FinalProject.entity.LoginUser;
import com.dev.FinalProject.repo.LoginRepo;


@Controller
public class LoginController {
	@Autowired
	LoginRepo repo;
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user",new LoginUser()); //html thymeleaf object is mapped to entity user object
		return "Viewlogin";
	}
	
	
	@PostMapping("/userLogin")
	public String loggedin(@ModelAttribute("user") LoginUser user,RedirectAttributes redirectAttributes) { 
		String userId=user.getUserId();
		LoginUser userdata=repo.findByUserId(userId);
		if(user.getPassword().equals(userdata.getPassword())) {
			return "Home";
		}else {
			redirectAttributes.addFlashAttribute("errorMsg", "Wrong username or password!");
			return "redirect:/";
		}
		
	}
	

}
