package com.adobe.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
public class UserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	
	private Integer userID;
	
	private String uuId;
	
	private LocalDateTime time;
	
	
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSession(Integer sessionId, Integer userID, String uuId, LocalDateTime time) {
		super();
		this.sessionId = sessionId;
		this.userID = userID;
		this.uuId = uuId;
		this.time = time;
	}
	
	

	public UserSession(Integer userID, String uuId, LocalDateTime time) {
		super();
		this.userID = userID;
		this.uuId = uuId;
		this.time = time;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SessionUser [sessionId=" + sessionId + ", userID=" + userID + ", uuId=" + uuId + ", time=" + time + "]";
	}
}
