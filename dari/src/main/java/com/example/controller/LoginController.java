package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "session")
@Controller(value ="LoginController")
@ELBeanName(value = "LoginController")
@Join(path = "/login", to = "/login.jsf")
public class LoginController {


}
