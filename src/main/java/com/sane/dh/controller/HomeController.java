package com.sane.dh.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sane.dh.model.User;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

	@RequestMapping(value = "/",method=RequestMethod.GET)
	public String home(HttpServletRequest request,HttpServletResponse response){
		return "home";
	}

	@RequestMapping(value = "/loginPage",method=RequestMethod.GET)
	public String loginPage(HttpServletRequest request,HttpServletResponse response){
	request.getCookies();
	Cookie cookie=new Cookie("sss", "ffff");
	cookie.setComment("dddd");
	cookie.setMaxAge(5);
	cookie.setVersion(1);
	response.addCookie(cookie);
	return "login";
	}
	@RequestMapping(value = "/jsonContnet",produces="application/json")
    @ResponseBody
	public User jsonContnet(){

        User userRegistInfo=new User();
        userRegistInfo.setUsername("dfdfsdfsd");
        userRegistInfo.setPassword("8888888");
        return userRegistInfo;
    }
    @RequestMapping("toUploadPage")
    public String toUploadPage(){
	return "upload";
	}
	@RequestMapping("upload")
	public String uploadImage(MultipartFile image) throws Exception {
	System.out.println(image.getContentType());
	System.out.println(image.getOriginalFilename());
	System.out.println(image.getName());
	return "uploadSuccess";
//	throw  new Exception("dddfd");
	}
}
