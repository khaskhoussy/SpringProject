package com.example.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import com.example.entity.Demande;
import com.example.entity.Relation;
import com.example.entity.User;
import com.example.service.DemandeService;
import com.example.service.FileStorageService;
import com.example.service.RelationService;
import com.example.service.UserService;


@Scope(value = "session")
@Controller(value ="ProfileController")
@ELBeanName(value = "ProfileController")
@Join(path = "/user/profile", to = "/pages/user/Profile.jsf")
public class Profile {
	
	 Logger logger = LoggerFactory.getLogger(Profile.class);
	
	 @Autowired
	 private UserService userService;
	 @Autowired
	 FileStorageService fileStorageService;
	 @Autowired
	 DemandeService demandeService;
	 @Autowired
	 private RelationService relationService;
	 
	 private User connectedUser ;
	 private String newPassword;
	 private String currentPassword;
	 private String repeatdPassword;
	 private String passwordResult;
	 private Part   myImage;
	 private List<User> allUsers;
	 private String searchInput;
	 public  List<User> users;
	 private String demandeResult;
	 public List<Demande> demandes;
	 private List<Demande> recivedDemandes;
	 public  List<User> usersWhoSendedMeRequests;
	 private String demandeEtat;
	 private int usersSize;
	 public List<Relation> relations;
	 public List<User> myFriends;
	 

	
	 public void  testwhoIsConnected()
	 {
		 connectedUser = HomeController.connectedUser; 
		 
		 recivedDemandes = demandeService.myReivedDemandes(connectedUser.getUserName());
		 for(int i=0;i<recivedDemandes.size();i++)
		 {
			 if(recivedDemandes.get(i).getDate()!= null)
				 recivedDemandes.remove(i);
		 }		 
		 usersWhoSendedMeRequests = new ArrayList<User>();
		 if(recivedDemandes != null)
		 recivedDemandes.forEach(d-> usersWhoSendedMeRequests.add(userService.getAllUsers().stream().filter(u->u.getId() == d.getId_Sender()).findFirst().get()));
		 relations = relationService.allRelation();
	 		
			usersSize = userService.getAllUsers().size()-1;
			}

	 
	 public void modify()
	 {		 
		 logger.info("UserName :"+connectedUser.getUserName()+"\t First name : "+connectedUser.getName()+"\t LastName :"+connectedUser.getLastName()+"\t CIN : "+connectedUser.getNic() + "\t Phone Number :"+connectedUser.getPhoneNumber());
		 userService.modifyUser(connectedUser.getUserName(),connectedUser);
	 }
	 
	 
	 public String passwordUpdate()
	 {
		 passwordResult ="Password Updated";
		 if(!connectedUser.getPassword().equals(currentPassword))
			{
				passwordResult= "your Current Password is False please check again";
				return passwordResult;
			}
		 if(!newPassword.equals(repeatdPassword))
			{
				passwordResult = "your Repeatd Password is Flase please check again";
				return 	passwordResult;
			}
		connectedUser.setPassword(repeatdPassword);
		userService.modifyUser(connectedUser.getUserName(),connectedUser);
		currentPassword="changed";
		return passwordResult;		
	 }
	
	 
	 public void addImage() throws IOException 
	 {	
	
		 myImage.write("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+myImage.getSubmittedFileName());		 		 
		 File oldFile=new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+myImage.getSubmittedFileName());
		 String AddedName=userService.getAlphaNumericString(7)+myImage.getSubmittedFileName();
		 File newfile =new File("C:\\Users\\aisce\\SpringProject\\dari\\src\\main\\webapp\\resources\\userImages\\"+AddedName);
		 oldFile.renameTo(newfile);		 
		 User userWithImage= new User();
		 userWithImage  = connectedUser;
		 userWithImage.setPhoto(AddedName);
		 userService.addUser(userWithImage);
		
	 }
	 
	 
	 public void searchForFriend ()
	 {
	 	
		 users = userService.getAllUsers();
		 allUsers=users.stream()
				 . filter(user->(user.getName().contains(searchInput)) || (user.getLastName().contains(searchInput) )||( user.getUserName().contains(searchInput)))
				 .collect(Collectors.toList());
		for (int i = 0;i<allUsers.size();i++)
		{
			if(allUsers.get(i).getId() == connectedUser.getId())
				allUsers.remove(i);
		}
		
	 
		 
	 }
	 
	 
	 public void sendRequest(String username)
	 {
		 
		 
		 demandeResult = "your Request hase been Sended";
		 demandes = demandeService.allDemande();
		 User reciver = users.stream().filter(u->u.getUserName().equals(username)).findAny().get();		 
		 Demande checked = demandes.stream().
				 filter(d->((d.getId_Sender()==connectedUser.getId()) || (d.getId_Sender()==reciver.getId()))
						 && ((d.getUser_Reciver().getId() == reciver.getId()) || (d.getUser_Reciver().getId() == connectedUser.getId()) )).findFirst().orElse(null);//with or else put default if nothing existe
		
		String result = (checked == null) ? "empty" : "notEmplty";
		 if (result.equals("empty"))
			demandeService.SendDemande(username, connectedUser.getUserName());
		 else 
			 demandeResult = "you already sended a request to this person ";			 
		
	 }
	 
	 public void changeDemandeEtat(String etat,int idSender)
	 {
		 		Demande selected = recivedDemandes.stream().filter(demande->demande.getId_Sender()== idSender).findFirst().get();
		 		selected.setEtat(etat);
		 		demandeService.changeStatus(selected, selected.getId());
		 		if(etat.equals("Accepted"))
		 		{
		 			relationService.addRelation(new Relation(false,connectedUser.getId(),idSender));
		 			
		 		}
		 		
		 		for(int i=0;i<usersWhoSendedMeRequests.size();i++)
		 		{
		 			if(usersWhoSendedMeRequests.get(i).getId() == idSender)
		 				usersWhoSendedMeRequests.remove(i);
		 		}
		 	
		 				
	 }
	 
	 public String checkingIfFriendMethod(int idUser)
	 {
	
				
				Relation result = relations.stream().
				filter(relation->((relation.getIdUser1() == idUser) && (relation.getIdUser2() == connectedUser.getId()))
																	||
						         ((relation.getIdUser2() == idUser) && (relation.getIdUser1() == connectedUser.getId()))).findFirst().orElse(null);
				if(result == null)
					return  "false";
				
					return "true";
			
	 }

	 public void searchInMyfriends()
	 {
		   
		
		  myFriends=relationService.myFriends(connectedUser.getUserName()).stream()
				 . filter(user->(user.getName().contains(searchInput)) || (user.getLastName().contains(searchInput) )||( user.getUserName().contains(searchInput)))
				 .collect(Collectors.toList());
	 }

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
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

	public Part   getMyImage() {
		return myImage;
	}

	public void setMyImage(Part   myImage) {
		this.myImage = myImage;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public String getDemandeResult() {
		return demandeResult;
	}

	public void setDemandeResult(String demandeResult) {
		this.demandeResult = demandeResult;
	}

	public List<Demande> getRecivedDemandes() {
		return recivedDemandes;
	}

	public void setRecivedDemandes(List<Demande> recivedDemandes) {
		this.recivedDemandes = recivedDemandes;
	}


	public List<User> getUsersWhoSendedMeRequests() {
		return usersWhoSendedMeRequests;
	}


	public void setUsersWhoSendedMeRequests(List<User> usersWhoSendedMeRequests) {
		this.usersWhoSendedMeRequests = usersWhoSendedMeRequests;
	}


	public String getDemandeEtat() {
		return demandeEtat;
	}


	public void setDemandeEtat(String demandeEtat) {
		this.demandeEtat = demandeEtat;
	}


	public int getUsersSize() {
		return usersSize;
	}


	public void setUsersSize(int usersSize) {
		this.usersSize = usersSize;
	}


	public List<User> getMyFriends() {
		return myFriends;
	}


	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}




	
	
	
	

}
