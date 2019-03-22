package com.dojo.java_exam_2.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dojo.java_exam_2.models.Idea;
import com.dojo.java_exam_2.models.User;
import com.dojo.java_exam_2.services.IdeaService;
import com.dojo.java_exam_2.services.UserService;



@Controller
public class Ideas {
	
	private UserService userService;
	private IdeaService ideaService;
	
	public Ideas (UserService userService, IdeaService ideaService) {
		this.userService = userService;
		this.ideaService = ideaService;
	}
	
	@GetMapping("/new_idea")
	public String new_idea(HttpSession session, Model model) {
		
		System.out.println("This user is: " + userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("this_user", userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("user", new User());
		model.addAttribute("idea", new Idea());
		
		return "new_idea.jsp";
	}
	
	@PostMapping("/process_idea")
	public String process_idea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println("Problem found in new idea.");
			return "/new_idea.jsp";
		}
		else {
			System.out.println("Creating new idea.");
			System.out.println(idea.getName());
			System.out.println(idea.getDescription());
			System.out.println(idea.getUser());
			
			ideaService.createIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("idea/{id}/edit")
	public String newIdea(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Idea idea = ideaService.findIdeaById(id);
		model.addAttribute("idea", idea);
		return "edit_idea.jsp";
	}
	
//	@PostMapping("/update_idea")
//	public String update_idea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
//		if(result.hasErrors()) {
//			System.out.println("Problem found in new idea.");
//			return "redirect:/new_idea";
//		}
//		else {
//			System.out.println("Creating new idea.");
//			System.out.println(idea.getName());
//			System.out.println(idea.getDescription());
//			System.out.println(idea.getUser());
//			
//			ideaService.updateIdea(idea);
//			return "redirect:/dashboard";
//		}
//	}
	
	@RequestMapping(value="/idea/{id}/update", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("ideas") Idea idea, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_idea.jsp";
        }else {
            ideaService.updateIdea(idea.getId(), idea.getName(), idea.getDescription());
            return "redirect:/dashboard";
        }
        
    }

	@GetMapping("show_idea/{id}")
	public String show_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		
		Idea idea = ideaService.findIdeaById(id);
		model.addAttribute("idea", idea);
		
		List<User> users = idea.getUsers();
		model.addAttribute("users", users);
		
		int numOfUsers = users.size();
		model.addAttribute(numOfUsers);
		System.out.println("Number of Users who liked this idea " + numOfUsers);
		
		User user = userService.findById((Long) session.getAttribute("id"));
		System.out.println("Displaying user: " + user.getUsername() +" "+ user.getId());
		System.out.println(idea.getUser().getId());
		return "show_idea.jsp";
	}
	
	@GetMapping("delete_idea/{id}")
	public String delete_idea(HttpSession session, @PathVariable("id") Long id) {
		
		Idea this_idea = ideaService.findIdeaById(id);
		
		ideaService.deleteIdea(this_idea);
				
		return "redirect:/dashboard";
	}
	
	@GetMapping("like_idea/{user_id}/{idea_id}")
	public String like_idea(HttpSession session, @PathVariable("user_id") Long user_id, @PathVariable("idea_id") Long idea_id) {
		System.out.println("User liked an idea");
		User user=userService.findById(user_id);
		Idea idea=ideaService.findIdeaById(idea_id);
		
		List<Idea> ideas = user.getIdea();
		ideas.add(idea);
		
		userService.updateUser(user);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("unlike_idea/{user_id}/{idea_id}")
	public String unlike_idea(HttpSession session, @PathVariable("user_id") Long user_id, @PathVariable("idea_id") Long idea_id) {
		System.out.println("User unliked an idea");
		User user = userService.findById(user_id);
		Idea idea = ideaService.findIdeaById(idea_id);
		
		List<Idea> ideas = user.getIdea();
	
		System.out.println(idea.getName());
		ideas.remove(idea);
		userService.updateUser(user);
		
		return "redirect:/dashboard";
	}
}
