package com.adobe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adobe.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	public Optional<User> findByMobile(String mobile);

	
}
