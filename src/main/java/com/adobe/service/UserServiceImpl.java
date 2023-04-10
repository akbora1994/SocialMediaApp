package com.adobe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.exceptions.LoginException;
import com.adobe.exceptions.UserException;
import com.adobe.model.User;
import com.adobe.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User createNewUser(User user) throws LoginException {
		
		Optional<User> opt =  userDao.findByMobile(user.getMobile());
		
		if(opt.isPresent()) {
			throw new LoginException("User already exists with this mobile number..");
		}
		
		User usr1 =  userDao.save(user);
		return usr1;
	}

	@Override
	public User getUserById(Integer id) throws UserException {
		return userDao.findById(id).orElseThrow(() -> new UserException("No user found with the user id : " + id));
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		List<User> userList = userDao.findAll();
		if (userList.size() == 0) {
			throw new UserException("User Unavailable!");
		}
		return userList;
	}

	@Override
	public User updateUserById(Integer id, User user) throws UserException {
		User existingUser = userDao.findById(id).orElseThrow(() -> new UserException("No user found with the user id : " + id));
		
		existingUser.setUserName(user.getUserName());
		existingUser.setBio(user.getBio());
		
		return userDao.save(existingUser);
	}

	@Override
	public String deleteUserById(Integer id) throws UserException {
		userDao.findById(id).orElseThrow(() -> new UserException("No user found with the user id : " + id));
		userDao.deleteById(id);
		return "User with id : " + id + " deleted successfully!";
	}


}
