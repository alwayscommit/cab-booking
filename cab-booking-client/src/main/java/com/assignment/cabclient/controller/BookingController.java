package com.assignment.cabclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingController {    
	
    @RequestMapping("/booking")
    public String loginMessage(){
        return "booking";
    }
}
