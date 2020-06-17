package com.example.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SlideEndEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ISimulationService;

@Named
@RequestScoped
public class SliderView {
    private String name;
	@Autowired
	ISimulationService iSimulationService;
    private float ajaxnb;
    private float ajaxnb2;
    private float ajaxnb3;

    private String name2;
    
    
    public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		System.out.println(name);
		this.name = name;
		System.out.println(name);
	}

	public float getAjaxnb2() {
		return ajaxnb2;
	}

	public void setAjaxnb2(float ajaxnb2) {
		this.ajaxnb2 = ajaxnb2;
	}

	public float getAjaxnb3() {
		return ajaxnb3;
	}

	public void setAjaxnb3(float ajaxnb3) {
		this.ajaxnb3 = ajaxnb3;
	}

	public float getAjaxnb() {
    	
    	
    	
    	
		return ajaxnb;
	}

	public void setAjaxnb(float ajaxnb) {
		this.ajaxnb = ajaxnb;
	}

	private int number1;
    private float number2 = 0.2f;  
    private int number3;   
    private int number4;   
    private int number5;
    private int number6;  
    private int number7;
    private int number8 = 30;
    private int number9 = 80;
 
    public int getNumber1() {
        return number1;
    }
 
    public void setNumber1(int number1) {
        this.number1 = number1;
    }
 
    public float getNumber2() {
        return number2;
    }
 
    public void setNumber2(float number2) {
        this.number2 = number2;
    }
 
    public int getNumber3() {
        return number3;
    }
 
    public void setNumber3(int number3) {
        this.number3 = number3;
    }
 
    public int getNumber4() {
        return number4;
    }
 
    public void setNumber4(int number4) {
        this.number4 = number4;
    }
 
    public int getNumber5() {
        return number5;
    }
 
    public void setNumber5(int number5) {
        this.number5 = number5;
    }
 
    public int getNumber6() {
        return number6;
    }
 
    public void setNumber6(int number6) {
        this.number6 = number6;
    }
 
    public int getNumber7() {
        return number7;
    }
 
    public void setNumber7(int number7) {
        this.number7 = number7;
    }
 
    public int getNumber8() {
        return number8;
    }
 
    public void setNumber8(int number8) {
        this.number8 = number8;
    }
 
    public int getNumber9() {
        return number9;
    }
 
    public void setNumber9(int number9) {
        this.number9 = number9;
    }
    
    
    
    public void onInputChanged2(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd2(SlideEndEvent event) {
    	
    	
  // 	ajaxnb2 = UserService.convertirPionts((int) event.getValue());
    	ajaxnb2 =(float)  event.getValue();
    	ajaxnb =iSimulationService.calculerTotal(ajaxnb3, ajaxnb2,name);
   
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    
    
    
    
    public void onInputChanged3(ValueChangeEvent event) {
    	System.out.println(ajaxnb);
    	System.out.println("azeaze");
    	System.out.println(event.getNewValue());
    
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd3(SlideEndEvent event) {
    	
    	
//    	ajaxnb = UserService.convertirPionts((int) event.getValue());
    	ajaxnb3 =(float)  event.getValue();
    	ajaxnb =iSimulationService.calculerTotal(ajaxnb3, ajaxnb2,name);
//    	
    	System.out.println(ajaxnb);
    	System.out.println(event.getValue());
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    
    public void valueChanged(ValueChangeEvent event) {
        //do your stuff
    	String naame =String.valueOf(event.getNewValue().toString());
    	System.out.println(naame);
    	System.out.println("kont lehné");

    	
    }
    

}