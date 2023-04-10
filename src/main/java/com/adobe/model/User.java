package com.adobe.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;
	
	@NotNull
	@NotBlank(message = "username is mandatory")
	private String userName;
	
	@NotNull
	@NotBlank
	private String email;
	
	private String bio;
	
	@CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
	private String password;
	
	@NotNull
	@Size(min=10,max=10,message = "Mobile number should be 10 digit")
	private String mobile;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
	
}
