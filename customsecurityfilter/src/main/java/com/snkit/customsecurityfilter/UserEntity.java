package com.snkit.customsecurityfilter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="microservice",name="security_user")
public class UserEntity implements Serializable {
	
	
	private static final long serialVersionUID = -8763977161007689038L;

	public UserEntity() {
		
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usrid")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	

	
	
	
}
