package com.example.controller;

import org.springframework.context.annotation.Scope;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.example.entity.Expert;
import com.example.entity.Expert_Insurance;
import com.example.entity.User;
import com.example.repository.BankRepository;
import com.example.repository.UserRepository;
import com.example.service.IInsuranceSimulationService;
import com.example.service.UserService;
@Scope(value = "session")
@RequestScoped
@Controller(value = "RegistrationController")
@ELBeanName(value = "RegistrationController")
//@Join(path = "/registration", to = "/pages/user/registration.jsf")
@Join(path = "/registration_expert", to = "/pages/user/registration_expert.jsf")
public class RegistrationController {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankRepository bankRepository;
	Logger logger = LoggerFactory.getLogger(Profile.class);

	private User newUser=new User();
	private String newPassword;
	private String repeatdPassword;
	private String passwordResult;

	public String addUser() {
		// System.out.println("repeatdPassword : "+repeatdPassword+"\n
		// newPassword : "+newPassword);
		if (repeatdPassword.equals(newPassword)) {
			newUser.setActive(true);
			newUser.setPhoto("index.png");
			newUser.setRoles("ROLE_USER");
			newUser.setPassword(newPassword);
			logger.info("userName :"+newUser.getUserName()+"\t name : "+newUser.getName()+"\t email : "+newUser.getMailAddress()+"\t newPassword : "+newPassword);
			userService.addUser(newUser);
			passwordResult = "your account has been added";
		} else
			passwordResult = "Please Check your password";

		
		return passwordResult;
	}

	
	
	
	////////////////////////////////////
	@Autowired
	BankRepository bankrepository;
	

	public List <String> bankNames;
	

	
	
	public List<String> getBankNames() {
		bankNames = bankrepository.findBankNames();
		return bankNames;
	}

	public void setBankNames(List<String> bankNames) {
		this.bankNames = bankNames;
	}

	private String bankName;
	
	public String getBankName() {
		System.err.println(bankName);
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
private String naame;

	public String getNaame() {
//		System.err.println(bankName);
	return naame;
}

public void setNaame(String naame) {
	this.naame = naame;
}


public void valueChanged(ValueChangeEvent event) {
    //do your stuff
	String naame =String.valueOf(event.getNewValue().toString());
	System.out.println(naame);
	System.out.println("kont lehné");

	
}

private String userName;
private String password;
private int phoneNumber;
private String name;
private String lastName;
private String roles;
private String mailAddress;
private int nic;
private boolean active;

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

	public String addExpert() {
		// System.out.println("repeatdPassword : "+repeatdPassword+"\n
		// newPassword : "+newPassword);
		if (repeatdPassword.equals(newPassword)) {
			newUser.setActive(true);
			newUser.setPhoto("index.png");
			newUser.setRoles("ROLE_EXPERT");
			newUser.setPassword(newPassword);
			newUser.setUserName(userName);
			newUser.setMailAddress(mailAddress);
			newUser.setName(name);
			newUser.setLastName(lastName);
			newUser.setPhoneNumber(phoneNumber);
			
			Expert expert = new Expert();
			System.err.println("i was here");
			expert.setBank_name(bankName);
//			expert.setBank_name("aezae");
			System.err.println("i was here");
			newUser.setExpert(expert);
			System.err.println("i was here");
			System.err.println(naame);
			System.err.println(name);
//			Expert expert =bankRepository.
//			Expert expert =bankRepository.findByName(user.getExpert().getBank_name();
//			newUser.setExpert(expert);
//			logger.info("userName :"+newUser.getUserName()+"\t name : "+newUser.getName()+"\t email : "+newUser.getMailAddress()+"\t newPassword : "+newPassword);
//			userService.addUser(newUser);
			userService.addUser(newUser);
			passwordResult = "your account has been added";
		} else
			passwordResult = "Please Check your password";

		
		return passwordResult;
	}
	
/////////////insurance Expert//////
	
	////////////////insuranceNamesList///////////////////
	private String insuranceName;
		
	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Autowired
	IInsuranceSimulationService iinsuranceSimulationService;
	
	
	 public List <String> insuranceNames;

	public List<String> getInsuranceNames() {
		insuranceNames = iinsuranceSimulationService.findInsuranceNames();
		return insuranceNames;
	}

	public void setInsuranceNames(List<String> insuranceNames) {
		this.insuranceNames = insuranceNames;
	}
	
   public void valueChanged2(ValueChangeEvent event) {
       //do your stuff
   	String naame =String.valueOf(event.getNewValue().toString());
   	System.out.println(naame);
   	System.out.println("kont lehné");

   	
   }
	
	public String addInsuranceExpert() {
		// System.out.println("repeatdPassword : "+repeatdPassword+"\n
		// newPassword : "+newPassword);
		if (repeatdPassword.equals(newPassword)) {
			newUser.setActive(true);
			newUser.setPhoto("index.png");
			newUser.setRoles("ROLE_IEXPERT");
			newUser.setPassword(newPassword);
			newUser.setUserName(userName);
			newUser.setMailAddress(mailAddress);
			newUser.setName(name);
			newUser.setLastName(lastName);
			newUser.setPhoneNumber(phoneNumber);
			
//			Expert expert = new Expert();
		    Expert_Insurance iexpert = new Expert_Insurance();
		    
			System.err.println("i was here");
			iexpert.setInsurance_name(insuranceName);
//			expert.setBank_name("aezae");
			System.err.println("i was here");
			newUser.setExpert_insurance(iexpert);
//			newUser.setExpert(expert);
			System.err.println("i was here");
			System.err.println(naame);
			System.err.println(name);

			userService.addUser(newUser);
			passwordResult = "your account has been added";
		} else
			passwordResult = "Please Check your password";

		
		return passwordResult;
	}
	
	
	
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatdPassword() {
		return repeatdPassword;
	}

	public void setRepeatdPassword(String repeatdPassword) {
		this.repeatdPassword = repeatdPassword;
	}

	public String getPasswordResult() {
		return passwordResult;
	}

	public void setPasswordResult(String passwordResult) {
		this.passwordResult = passwordResult;
	}

}
