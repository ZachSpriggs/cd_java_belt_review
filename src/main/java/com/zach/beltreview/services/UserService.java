package com.zach.beltreview.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.zach.beltreview.models.User;
import com.zach.beltreview.models.Event;
import com.zach.beltreview.models.Message;
import com.zach.beltreview.repositories.UserRepo;
import com.zach.beltreview.repositories.EventRepo;
import com.zach.beltreview.repositories.MessageRepo;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final EventRepo eventRepo;
    private final MessageRepo messageRepo;
    
    public UserService(UserRepo userRepo, EventRepo eventRepo, MessageRepo messageRepo) {
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
        this.messageRepo = messageRepo;
    }
    
    ////// User methods //////
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    //////// user methods ////////
    public void updateUser(User user) {
    	userRepo.save(user);
    }

    
    /////// event methods ///////
    public Event createEvent(Event event) {
    	return eventRepo.save(event);
    }
    
    public void updateEvent(Event event) {
    	eventRepo.save(event);
    }
    
    public List<Event> allEvents() {
    	return eventRepo.findAll();
    }
    
    public Event findEventById(Long id) {
    	Optional<Event> event = eventRepo.findById(id);
    	if(event.isPresent()) {
            return event.get();
    	} else {
    	    return null;
    	}
    }
    
    public Event editEvent(Long id, String title, Date date, String state, String location) {
    	Event event = findEventById(id);
    	event.setTitle(title);
    	event.setEventDate(date);
    	event.setState(state);
    	event.setLocation(location);
    	return eventRepo.save(event);
    }
    
    
    public void deleteEvent(Long id) {
    	eventRepo.deleteById(id);
    }
    
    public int guestCount(List<User> guests) {
    	int count = 0;
    	for(int i = 0; i < guests.size(); i++) {
    		count += i;
    	}
    	return count;
    }
    
    
    //////// message methods ////////
    public Message createMessage(Message message) {
    	return messageRepo.save(message);
    }
    
    public void updateMessage(Message message) {
    	messageRepo.save(message);
    }
    
    
    
    
}