package com.zach.beltreview.models;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Transient;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    
    @Size(min = 3, max = 20, message = "First Name must be at least 3 characters long")
    private String firstName;
    
    @Size(min = 3, max = 20, message = "Last Name must be at least 3 characters long")
    private String lastName;
    
    @Size(min = 2, max = 20, message = "Location must be at least 2 characters long")
    private String location;

	private String state;
    
	@NotBlank
	@Size(min = 5, max = 40)
    @Email(message = "Email must be valid")
    private String email;
    
    @Size(min = 5, message = "Password must be greater than 5 characters")
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "event_users",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "event_id")
    		)
    private List<Event> events;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Event> hostedEvents;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Message> messages;
    
    
    public User() {
    }
    
    

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	 public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getLocation() {
			return location;
		}


		public void setLocation(String location) {
			this.location = location;
		}


		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public List<Event> getEvents() {
			return events;
		}


		public void setEvents(List<Event> events) {
			this.events = events;
		}


		public List<Event> getHost() {
			return hostedEvents;
		}


		public void setHost(List<Event> hostedEvents) {
			this.hostedEvents = hostedEvents;
		}


		public List<Message> getMessages() {
			return messages;
		}


		public void setMessages(List<Message> messages) {
			this.messages = messages;
		}
		
		
}
    