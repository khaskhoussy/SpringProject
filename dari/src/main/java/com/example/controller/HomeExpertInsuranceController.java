package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "session")
@Controller(value ="expertInsuranceController")
@ELBeanName(value = "expertInsuranceController")
@Join(path = "/expert_insurance/Home", to = "/pages/expert_insurance/Home.jsf")
public class HomeExpertInsuranceController {

}
