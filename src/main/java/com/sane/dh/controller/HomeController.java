package com.sane.dh.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
@RequestMapping(method=RequestMethod.GET)
	public String homePage(HttpServletRequest request,HttpServletResponse response){
	request.getCookies();
	Cookie cookie=new Cookie("sss", "ffff");
	cookie.setComment("dddd");
	cookie.setMaxAge(5);
	cookie.setVersion(1);
	response.addCookie(cookie);
		return "home";
	}
}
