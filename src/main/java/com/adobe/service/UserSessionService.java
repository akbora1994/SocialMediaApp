package com.adobe.service;

import com.adobe.exceptions.LoginException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Login;

public interface UserSessionService {

    public String logIntoAccount(Login login) throws UserException, LoginException;
	
	public String logoutFromAccount(String uuid) throws LoginException;
}
