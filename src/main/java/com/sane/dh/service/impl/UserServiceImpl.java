package com.sane.dh.service.impl;

import java.util.Date;
import java.util.List;

import com.sane.dh.dao.UserMapper;
import com.sane.dh.model.ImUserDetails;
import com.sane.dh.model.User;
import com.sane.dh.model.UserCriteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sane.dh.model.email.EmailBean;
import com.sane.dh.service.UserService;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService{
	Logger logger=Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userDao;
	@Value("${mail.from}")
	private String fromEmailAddress;
	@Override
	public Boolean saveNewUser(User userRegistInfo) {
		userRegistInfo.setCreatedate(new Date());
		userRegistInfo.setEnable(Boolean.TRUE);
		int count=userDao.insertSelective(userRegistInfo);
		if (count==1) {
			return  true;
		}
		return  false;
	}
	@Override
	public User getUserInfo(String userName) {
		UserCriteria userCriteria=new UserCriteria();
		UserCriteria.Criteria userSql=userCriteria.createCriteria();
		userSql.andUsernameEqualTo(userName);
		List<User> userList=userDao.selectByExample(userCriteria);
		if(!CollectionUtils.isEmpty(userList)){
			return userList.get(0);

		}else{
			return  null;
		}
	}
	@Override
	public boolean userNameIsRegisted(String userName) {
		UserCriteria userCriteria=new UserCriteria();
		UserCriteria.Criteria userSql=userCriteria.createCriteria();
		userSql.andUsernameEqualTo(userName);
		int count=userDao.countByExample(userCriteria);
		return count>0;
	}
	
	public boolean sendValidEmail(EmailBean emailBean){
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(emailBean.getEmailAddress());
		simpleMailMessage.setSubject(emailBean.getSubject());
		simpleMailMessage.setFrom(fromEmailAddress);
		simpleMailMessage.setText(emailBean.getText());
		simpleMailMessage.setSentDate(new Date());
		try {
//			mailSendr.send(simpleMailMessage);
			logger.info(">>>>>>>>>>发送验证邮件成功：用户邮箱："+emailBean.getEmailAddress());
			return true;
		} catch (Exception e) {
			logger.info("发送验证邮件失败:"+emailBean.getEmailAddress());
			return false;
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.getUserInfo(username);
		UserDetails userDetails=null;
		if(user!=null){
			userDetails=new ImUserDetails(user);
		}
		return userDetails;
	}
}
