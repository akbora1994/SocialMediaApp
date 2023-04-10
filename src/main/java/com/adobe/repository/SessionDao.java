package com.adobe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adobe.model.UserSession;

@Repository
public interface SessionDao extends JpaRepository<UserSession, Integer>{

    public Optional<UserSession> findByUserID(Integer userID);
	
	public Optional<UserSession> findByUuId(String uuId);
}
