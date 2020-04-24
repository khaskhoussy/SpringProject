package com.example.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "session")
@Controller(value ="employeControllerr")
@ELBeanName(value = "employeControllerr")
@Join(path = "/admin/test", to = "/pages/admin/otherTest.jsf")
public class otherTest {

}
