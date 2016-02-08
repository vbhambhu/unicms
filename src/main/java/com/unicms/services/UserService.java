package com.unicms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.unicms.models.User;


import java.util.List;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserService {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	


	public List<User> listUser() {
		
		String SQL = "SELECT u.id,u.username,u.email,u.first_name,u.last_name,u.status,u.created_at,ur.role_name FROM users u LEFT JOIN user_roles ur ON (u.role_id = ur.role_id) ORDER BY u.created_at DESC";
		List <User> users = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<User>(User.class));
		return users;
	}
	
	
	public List<User> listRoles() {
		
	      String SQL = "SELECT * FROM user_roles";
	      
	      List<User> users = jdbcTemplate.query( SQL, new BeanPropertyRowMapper<User>(User.class));
	      
	      return users;
	}


	public boolean emailExists(String email) {
		
		String SQL = "SELECT count(*) FROM users WHERE email = ?";
		boolean result = false;

		int count = jdbcTemplate.queryForObject(SQL, new Object[] { email }, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;
		
	}


	public boolean usernameExists(String username) {
		
		String SQL = "SELECT count(*) FROM users WHERE username = ?";
		boolean result = false;

		int count = jdbcTemplate.queryForObject(SQL, new Object[] { username }, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;
		
	}


	public void createUser(User user) {
		
		   DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Date dateobj = new Date();
	   
		   String SQL = "INSERT INTO users (username,password, email,first_name,last_name,role_id,status,created_at) values (?,?,?,?,?,?,?,?)";
		   
		   jdbcTemplate.update(SQL, new Object[] { 
				   user.getUsername(),
				   user.getPassword(),
				   user.getEmail(),
				   user.getFirstName(),
				   user.getLastName(),
				   user.getRoleId(),
				   1,
				   date_format.format(dateobj) });
		   
		   return;
		
	}


	public User getUserById(int id) {
		
		//String SQL = "SELECT * from users where id = ?";
		
		String SQL = "SELECT u.id,u.username,u.email,u.first_name,u.last_name,u.status,u.created_at,ur.role_name FROM users u LEFT JOIN user_roles ur ON (u.role_id = ur.role_id) WHERE u.id= ?";
		
		User user = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class) );
		return user;

	}
	

}
