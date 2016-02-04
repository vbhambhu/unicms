package com.unicms.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
	
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	
	public List<User> listUser() {
		
	      String SQL = "select * from users";
	      List <User> users = jdbcTemplate.query(SQL, new UserMapper());
	      return users;
	}
	
	
	public List<Object> listRoles() {
		
	      String SQL = "select * from users WHERE role_id = ?";
	      
	      List<Object> users = jdbcTemplate.query( SQL, new Object[] { "1" }, (rs, rowNum) -> new User());
	      
	      return users;
	}

	

	public User findAll() {
		
		return null;
	}
	

}
