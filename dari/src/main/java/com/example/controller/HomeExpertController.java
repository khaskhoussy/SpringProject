package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "session")
@Controller(value ="expertController")
@ELBeanName(value = "expertController")
@Join(path = "/expert/Home", to = "/pages/expert/Home.jsf")
public class HomeExpertController {

}
