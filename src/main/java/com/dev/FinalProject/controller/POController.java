package com.dev.FinalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.FinalProject.entity.po;

@Controller
public class POController {
	@Autowired
	CO1Controller co1;
	
	@Autowired
	CO2Controller co2;
	
	@Autowired
	CO3Controller co3;
	
	@Autowired
	CO4Controller co4;
	
	@Autowired
	CO5Controller co5;
	
	
	@GetMapping("/pocalculate")
	public String loginwe(Model model) {
		model.addAttribute("thobj",new po()); //html thymeleaf object is mapped to entity user object
		return "POpage";
	}
	@PostMapping("/COcal")
	public String loggedin(@ModelAttribute("thobj") po user,RedirectAttributes redirectAttributes) { 
		double var1=user.getI1();
		double var2=user.getI2();
		double var3=user.getI3();
		double var4=user.getI4();
		double var5=user.getI5();
		
		double lamb1=co1.giveavg1();
		double lamb2=co2.giveavg2();
		double lamb3=co3.giveavg3();
		double lamb4=co4.giveavg4();
		double lamb5=co5.giveavg5();
		
		double poresult=((var1*lamb1)+(var2*lamb2)+(var3*lamb3)+(var4*lamb4)+(var5*lamb5))/(var1+var2+var3+var4+var5);
		redirectAttributes.addFlashAttribute("POmessage", poresult);
		
		
		System.out.println(lamb1);
		System.out.println(var1);
		
		System.out.println(lamb2);
		System.out.println(var2);
		
		System.out.println(lamb3);
		System.out.println(var3);
		
		System.out.println(lamb4);
		System.out.println(var4);
		
		System.out.println(lamb5);
		System.out.println(var5);
		
		System.out.println(poresult);
		
		
		
		return "redirect:/pocalculate";
	}
}