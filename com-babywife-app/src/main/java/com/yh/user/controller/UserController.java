package com.yh.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/test",method=RequestMethod.GET)  
    public String  test(HttpServletRequest request,String  userName){  
       
       
        
       
        return "Hello World！！！";  
    }  

}
