package com.sane.dh.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sane.dh.dao.UserDao;
import com.sane.dh.model.email.EmailBean;
import com.sane.dh.model.user.UserIdType;
import com.sane.dh.model.user.UserRegistInfo;
import com.sane.dh.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	Logger logger=Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Value("${mail.from}")
	private String fromEmailAddress;
	@Autowired
	@Qualifier(value="mailSender")
	private JavaMailSender mailSendr;
	@Override
	public Boolean saveNewUser(UserRegistInfo userRegistInfo) {
		boolean insertResult=userDao.addUser(userRegistInfo);
		
		if (insertResult) {
			if (userRegistInfo.getUserIdType().equals(UserIdType.EMAIL)) {
				EmailBean emailBean= new EmailBean();
				emailBean.setEmailAddress(userRegistInfo.getEmail_phone());
				emailBean.setSubject("WebDesign 注册验证");
				emailBean.setText("请点击下面的链接进行用户验证");
				sendValidEmail(emailBean);
			}else if (UserIdType.PHONE.equals(userRegistInfo.getUserIdType())) {
				//发送短信验证码
			}
			
		}
		return  insertResult;
	}
	@Override
	public UserRegistInfo getUserInfo(String email_phone) {
		return userDao.getUserByEmail_phone(email_phone);
	}
	@Override
	public boolean phone_emailIsRegisted(String email_phone) {
		return userDao.email_phoneIsRegisted(email_phone);
	}
	
	public boolean sendValidEmail(EmailBean emailBean){
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(emailBean.getEmailAddress());
		simpleMailMessage.setSubject(emailBean.getSubject());
		simpleMailMessage.setFrom(fromEmailAddress);
		simpleMailMessage.setText(emailBean.getText());
		simpleMailMessage.setSentDate(new Date());
		try {
			mailSendr.send(simpleMailMessage);
			logger.info(">>>>>>>>>>发送验证邮件成功：用户邮箱："+emailBean.getEmailAddress());
			return true;
		} catch (Exception e) {
			logger.info("发送验证邮件失败:"+emailBean.getEmailAddress());
			return false;
		}
		
	}

}
