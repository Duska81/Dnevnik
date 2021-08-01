package com.iktpreobuka.dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.models.EmailObject;
import com.iktpreobuka.dnevnik.services.EmailService;

@RestController 

@RequestMapping(path = "/Dnevnik/email") 

	public class EmailController { 
		 
		@Autowired 
		private EmailService emailService; 
		 
		
		 
		@RequestMapping(method = RequestMethod.POST, value = "/simpleEmail") 
		public String sendSimpleMessage(@RequestBody EmailObject object) {  
			if(object==null || object.getTo()==null || object.getText()==null) {   
				return null;   
				}             
			emailService.sendSimpleMessage(object);      
			return "mail je poslat!"; 
		}
		
		
}
