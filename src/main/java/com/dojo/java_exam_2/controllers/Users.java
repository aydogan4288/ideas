package com.dojo.java_exam_2.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dojo.java_exam_2.models.Idea;
import com.dojo.java_exam_2.models.User;
import com.dojo.java_exam_2.services.IdeaService;
import com.dojo.java_exam_2.services.UserService;

@Controller
public class Users {

	private final UserService userService;
	private final IdeaService ideaService;
	public Users(UserService userService, IdeaService ideaService) {
		
		this.userService = userService;
		this.ideaService = ideaService;
	}

	@GetMapping("/")
	public String login_registration(Model model) {
		System.out.println("\n\n<<-------------Login and Registration Form------------->>\n\n");
		model.addAttribute("user", new User());
		
		return "login_registration.jsp";
	}
	
	@PostMapping("/process_registration")
	public String process_registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		System.out.println("\n\n<<-------------Processing User Registration------------->>\n\n");
		
		System.out.println("Username: " + user.getUsername());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: " + user.getPassword());
		
		if(result.hasErrors()) {
			System.out.println("Problem found in registration.");
			return "login_registration.jsp";
		}
		
		else {
			System.out.println("Registration Succesfull!");
			userService.createUser(user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("id", user.getId());
			session.setAttribute("user", userService.findById(user.getId()));
			
			System.out.println("id created" + session.getAttribute("id"));
			return "redirect:/dashboard";
		}
		
	}
	
	@PostMapping("process_login")
	public String process_login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		
		System.out.println("\n\n<<-------------Processing User Login------------->>\n\n");
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: "  +user.getPassword());
		
		if(result.hasErrors()) {
			System.out.println("Problem found in registration.");
			return "login_registration.jsp";
		}
		else if(userService.authenticateUser(user.getEmail(), user.getPassword())) {
			System.out.println("Authenticating User");
			
			session.setAttribute("username", userService.findByEmail(user.getEmail()).getUsername());
			session.setAttribute("id", userService.findByEmail(user.getEmail()).getId());
			
			System.out.println(session.getAttribute("id"));
			System.out.println("Username: " + session.getAttribute("username"));
			return "redirect:/dashboard";
		}
		else {
			return "login_registration.jsp";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("\n\n<<-------------Logging Out User------------->>\n\n");
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		
		if(session.getAttribute("id")==null) {
			return "redirect:/";
		}
		
		List<Idea> ideas = ideaService.findAllIdeas();
		model.addAttribute("ideas", ideas);
		
		User user = userService.findById((Long) session.getAttribute("id"));
		System.out.println("Displaying user: " + user.getUsername());
		
		List<Idea> likes = user.getIdea();
		model.addAttribute("likes", likes);
		
		
		return "index.jsp";
	}
	
}
