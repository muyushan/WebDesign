package com.sane.dh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sane.dh.model.user.UserRegistInfo;
import com.sane.dh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/regist" }, method = RequestMethod.GET)
	public String showRegistPage() {
		return "regist";
	}

	@RequestMapping(value = { "/regist" }, method = RequestMethod.POST)
	@ResponseBody
	public String processRegist(@RequestBody UserRegistInfo registInfo) {
		if (userService.saveNewUser(registInfo)) {
			return "success";
		} else {
			return "failer";
		}
	}

	/**
	 * 验证将要注册的电话或者email是没有人注册过的。
	 * @return true:已经注册过了 <br>false：尚未注册过
	 */
	@RequestMapping(value="/{email_phone}/validate")
	@ResponseBody
	public String validateEmail_PhoneIsNew(@PathVariable String email_phone) {
		Boolean isRegisted=userService.phone_emailIsRegisted(email_phone);
		return isRegisted.toString(); 
	}

	/**
	 * 个人信息页
	 * 
	 * @param email_phone
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/{email_phone}" }, method = RequestMethod.GET)
	public String showProfile(@PathVariable(value = "email_phone") String email_phone, Model model) {
		UserRegistInfo userRegistInfo = userService.getUserInfo(email_phone);
		model.addAttribute("userInfo", userRegistInfo);
		return "userProfile";
	}

}
