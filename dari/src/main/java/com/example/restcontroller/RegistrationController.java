package com.example.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.MailService;



@Controller
public class RegistrationController {
    @Autowired
    private MailService emailService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/sendmail/{id}")
    public String sendmail(@PathVariable("id") int idUser) {
    	User user = userRepository.findById(idUser).get();
        emailService.sendMail(user.getMailAddress(), "Insurance Confirmed", "Hello Mr/Mrs"+user.getLastName()+"\n Your Insurance has been paid and confirmed");
        return "emailsent";
    }
}
