package com.sane.dh.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.code.kaptcha.Producer;
import com.sane.dh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class HomeController {
	@Autowired
	private Producer producer;

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
	@RequestMapping("captcha.jpg")
	public void getCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String captchaText=producer.createText();
		request.getSession().setAttribute("captchaText",captchaText);
		try(ServletOutputStream outputStream=response.getOutputStream();
		){
			BufferedImage bufferedImage=producer.createImage(captchaText);
			response.setContentType("image/jpeg");
			ImageIO.write(bufferedImage,"jpg",outputStream);
		}
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
	@RequestMapping("loginerror")
	public String loginError(){
		return "loginError";
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
