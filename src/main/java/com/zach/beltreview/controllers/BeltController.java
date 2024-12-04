package com.zach.beltreview.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zach.beltreview.models.Event;
import com.zach.beltreview.models.Message;
import com.zach.beltreview.models.User;
import com.zach.beltreview.services.UserService;
import com.zach.beltreview.validators.UserValidator;

@Controller
public class BeltController {

	private final UserService userService;
	private final UserValidator userValidator;
	
	public BeltController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	
	@RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "loginReg.jsp";
    }
    
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        // if result has errors, return the registration page (don't worry about validations just now)
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		return "redirect:/";
    	} else {
    		User u = userService.registerUser(user);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    		
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User user = userService.findByEmail(email);
    		session.setAttribute("userId", user.getId());
    		return "redirect:/home";
    	} else {
    		model.addAttribute("error", "Incorrect email or password, please try again");
    		return "loginReg.jsp";
    	}
        // else, add error messages and return the login page
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model, @Valid @ModelAttribute("event") Event event) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Long id = (Long) session.getAttribute("userId");
    	User user = userService.findUserById(id);
    	model.addAttribute("user", user);
    	List<Event> events = userService.allEvents();
    	model.addAttribute("event", event);
    	List<Event> instate = new ArrayList<Event>();
    	List<Event> outstate = new ArrayList<Event>();
    	for(Event loc: events) {
    		if(loc.getState().equals(user.getState())) {
        		instate.add(loc);
        	} else {
        		outstate.add(loc);
        	}
    	}
    	model.addAttribute("instate", instate);
    	model.addAttribute("outstate", outstate);    	
    	return "homePage.jsp";
    }
    
   @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
      		if(result.hasErrors()) {
      			return "redirect:/home";
      		}
		    	userService.createEvent(event);
		    	return "redirect:/home";
	}

   
   @RequestMapping("/showEvent/{id}")
   public String showEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
	   if(session.getAttribute("userId") == null) {
   		return "redirect:/";
   	}
	   Long userId = (Long) session.getAttribute("userId");
	   User user = userService.findUserById(userId);
	   Event event = userService.findEventById(id);
	   List<Message> messages = event.getMessages();
	   List<User> guests = event.getGuests();
	   int count = userService.guestCount(guests);
	   model.addAttribute("count", count);
	   model.addAttribute("event", event);
	   model.addAttribute("user", user);
	   model.addAttribute("guests", guests);
	   model.addAttribute("messages", messages);
	   model.addAttribute("message", new Message());
	   return "showEvent.jsp";
   }
   
   
   @RequestMapping("/home/{id}/join")
   public String joinEvent(@PathVariable("id") Long id, HttpSession session) {
	   Long thisUser = (Long) session.getAttribute("userId");
	   User user = userService.findUserById(thisUser);
	   Event event = userService.findEventById(id);
	   List<User> guests = event.getGuests();
	   guests.add(user);
	   userService.updateUser(user);
	   return "redirect:/home";
   }
   
   
   @RequestMapping("/editEvent/{id}/edit")
   public String editPage(@PathVariable("id") Long id, @ModelAttribute("event") Event event, Model model, HttpSession session) {
	   if(session.getAttribute("userId") == null) {
		   return "redirect:/";
	   }
	   Long userId = (Long) session.getAttribute("userId");
	   User user = userService.findUserById(userId);
	   model.addAttribute("user", user);
	   model.addAttribute("event", userService.findEventById(id));
	   return "edit.jsp";
   }
   
   @RequestMapping(value = "/editEvent/{id}", method = RequestMethod.PUT)
   public String editEvent(@PathVariable("id") Long id, @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
	   Long userId = (Long) session.getAttribute("userId");
	   User user = userService.findUserById(userId);
	   if(userService.findEventById(id).getUser().getId() == user.getId()) {
		   if(result.hasErrors()) {
			   return "edit.jsp";
		   } else {
			   
			   Event editEvent = event;
			   model.addAttribute("event", editEvent);
			   model.addAttribute("user", user);
			   event.setUser(user);
			   event.setGuests(event.getGuests());
			   userService.updateEvent(event);
			   return "redirect:/home";
		   }
	   } else {
		   return "redirect:/home";
	   }
   }
   
    
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public String addMessage(@Valid @ModelAttribute("message") Message message, BindingResult result) {
    	if(result.hasErrors()) {
    		return "redirect:/home";
    	}
		userService.createMessage(message);
		return "redirect:/home";
    }
    
    
    @RequestMapping("/home/{id}/cancel")
    public String cancelEvent(@PathVariable("id") Long id, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	User user = userService.findUserById(userId);
    	Event event = userService.findEventById(id);
    	List<User> guests = event.getGuests();
    	for(int i = 0; i < guests.size(); i++) {
    		if(guests.get(i).getId() == user.getId()) {
    			guests.remove(i);
    		}
    	}
    	event.setGuests(guests);
    	userService.updateUser(user);
    	return "redirect:/home";
    }
    
    
    @RequestMapping("/home/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	Event event = userService.findEventById(id);
    	for(User user: event.getGuests()) {
    		List<Event> myEvents = user.getEvents();
    		myEvents.remove(event);
    		user.setEvents(myEvents);
    		userService.updateUser(user);
    	}
    	userService.deleteEvent(id);
    	return "redirect:/home";
    }
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
    	return "redirect:/";
        // redirect to login page
    }
    
    
}
	

