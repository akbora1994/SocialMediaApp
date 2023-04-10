package com.adobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.exceptions.LoginException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Login;
import com.adobe.model.UserSession;
import com.adobe.repository.SessionDao;
import com.adobe.repository.UserDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class UserSessionServiceImpl implements UserSessionService{

	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public String logIntoAccount(Login login) throws UserException, LoginException {
		
		Optional<com.adobe.model.User> checkUser =  userDao.findByMobile(login.getMobile());
		
		if(!checkUser.isPresent()) {
			throw new UserException("User not exist!");
		}
		
		Optional<UserSession> optUser =  sessionDao.findByUserID(checkUser.get().getUserID());
		
		if(optUser.isPresent()) {
			throw new LoginException("User already exists!");
		}
		
		if(login.getMobile().equals(checkUser.get().getMobile()) && login.getPassword().equals(checkUser.get().getPassword())) {
			
			String key = RandomString.getRandomString();
			UserSession userSess = new UserSession(checkUser.get().getUserID(), key, LocalDateTime.now());
			
			sessionDao.save(userSess);
			return userSess.toString();
		}else {
			throw new LoginException("Invalid Username And password..Please try again!");
		}
		
	}

	@Override
	public String logoutFromAccount(String uuid) throws LoginException {
		
		List<UserSession> findUser =  sessionDao.findAll();
		
		Optional<UserSession> currUser =  sessionDao.findByUuId(uuid);
		if(!currUser.isPresent()) {
			throw new LoginException("Please login first!");
		}
		
		sessionDao.delete(currUser.get());
		List<UserSession> ad =  sessionDao.findAll();
		
		if(ad.size()==findUser.size()) {
			throw new LoginException("Invalid!");
		}
		return "Logged Out!";
	}
}
