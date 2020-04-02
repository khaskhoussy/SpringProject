package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String userName;
	private String password;
	private int phoneNumber;
	private String name;
	private String lastName;
	private String roles;
	private String mailAddress;
	private int nic;
	private boolean active;
	
	
	
	
	@OneToMany(mappedBy="userReciver",cascade = CascadeType.ALL)
	private List<Demande> Demandes;
	

	
	@OneToMany(mappedBy="userReciver",cascade = CascadeType.ALL)
	private List<Message> messages;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Reclamation> Reclamations;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<CommentsAnnonce> commentsAnnonces;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Announce> Announces;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Appointment> Appointments;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Rent> Rents;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<SearchHistory> searchHistorys;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Simulation> simulations;
	
	@OneToMany(mappedBy="users",cascade = CascadeType.ALL)
	private List<Announce> Announcess;
		
	
	public int getId() {
		return id;
	}
	
	
	
	public User() {
		super();
	}



	public User(String userName, String password, int phoneNumber, String name, String lastName, String roles,
			String mailAddress, int nic) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.lastName = lastName;
		this.roles = roles;
		this.mailAddress = mailAddress;
		this.nic = nic;
	}



	public User(int id, String userName, String password, int phoneNumber, String name, String lastName, String roles,
			String mailAddress, int nic) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.lastName = lastName;
		this.roles = roles;
		this.mailAddress = mailAddress;
		this.nic = nic;
	}



	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public int getNic() {
		return nic;
	}
	public void setNic(int nic) {
		this.nic = nic;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
