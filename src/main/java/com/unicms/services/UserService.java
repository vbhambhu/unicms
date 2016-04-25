package com.unicms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.unicms.models.User;


import java.util.List;
import java.awt.print.Pageable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserService {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	


	public List<User> listUser(int limit, int offset, String sortBy, String order, String query) {
		
		String SQL = null;
		
		if (query.trim().length() > 0){
			
			query = "'%" +query.toLowerCase().trim() + "%'";
			SQL = String.format("SELECT u.id,u.username,u.email,u.first_name,u.last_name,u.status,u.created_at,ur.role_name FROM users u LEFT JOIN user_roles ur ON (u.role_id = ur.role_id) WHERE username LIKE %s OR first_name LIKE %s OR last_name LIKE %s or email LIKE %s ORDER BY %s %s LIMIT ?,?",query,query,query,query,sortBy, order);
			
			 List <User> users = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<User>(User.class), offset, limit);
			return users;
			
			
		} else {
			
			 SQL = String.format("SELECT u.id,u.username,u.email,u.first_name,u.last_name,u.status,u.created_at,ur.role_name FROM users u LEFT JOIN user_roles ur ON (u.role_id = ur.role_id) ORDER BY %s %s LIMIT ?,?", sortBy, order);
			List <User> users = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<User>(User.class),  offset, limit);
			return users;
			
			
		}
		
		
		
		
		
	}
	
	public int count(String query){
		
		String SQL = null;
		
		if (query.trim().length() > 0){
			
			query = "'%" +query.toLowerCase().trim() + "%'";
			
			 SQL = String.format("SELECT count(id) FROM users WHERE username LIKE %s OR first_name LIKE %s OR last_name LIKE %s or email LIKE %s", query,query,query,query);
			 return (jdbcTemplate.queryForObject(SQL, Integer.class));
			
		} else {
			 SQL = "SELECT count(id) FROM users";
			 return (jdbcTemplate.queryForObject(SQL, Integer.class));
		} 
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
		
		String SQL = "SELECT count(*) FROM users WHERE LOWER(username) = ?";
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
	       
	       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	       String hashedPassword = passwordEncoder.encode(user.getPassword());
	   
		   String SQL = "INSERT INTO users (username,password, email,first_name,last_name,role_id,status,created_at) values (?,?,?,?,?,?,?,?)";
		   
		   jdbcTemplate.update(SQL, new Object[] { 
				   user.getUsername(),
				   hashedPassword,
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
		
		String SQL = "SELECT u.id,u.username,u.email,u.first_name,u.last_name,u.status,u.created_at,ur.role_id, ur.role_name FROM users u LEFT JOIN user_roles ur ON (u.role_id = ur.role_id) WHERE u.id= ?";
		
		User user = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class) );
		return user;

	}
	
	public void deleteUser(int id){
		
		 String SQL = "DELETE FROM users where id = ?";
		 jdbcTemplate.update(SQL, id);
		 
	}
	
	
	
	public int emailCountByEmail(String email) {
		
		String SQL = "SELECT count(*) FROM users WHERE email = ?";
		
		return jdbcTemplate.queryForObject(SQL, new Object[] { email }, Integer.class);
		
	}


	public int usernameCountByUsername(String username) {
		
		String SQL = "SELECT count(*) FROM users WHERE username = ?";
		
		return jdbcTemplate.queryForObject(SQL, new Object[] { username }, Integer.class);
				
	}
	
	
	public void updateUser(User user){
		
		DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dateobj = new Date();
	    
	    if(user.getPassword() != null){
	    	
	    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	  	    String hashedPassword = passwordEncoder.encode(user.getPassword());
	    	
	    	String SQL = "UPDATE users SET username = ?, email = ?, password = ?, first_name = ?,last_name = ?,role_id = ?, status = ?, updated_at = ?  WHERE id = ?";
	    	jdbcTemplate.update(SQL, user.getUsername(),
		    		  user.getEmail() , 
		    		  hashedPassword,
		    		  user.getFirstName(),
		    		  user.getLastName(),
		    		  user.getRoleId(),
		    		  user.getStatus(),
		    		  date_format.format(dateobj),
		    		  user.getId()
		    		  );
	 
	    } else {
	    	
	    	String SQL = "UPDATE users SET username = ?, email = ?,first_name = ?,last_name = ?,role_id = ?, status = ?, updated_at = ?  WHERE id = ?";
	    	jdbcTemplate.update(SQL, user.getUsername(),
		    		  user.getEmail() , 
		    		  user.getFirstName(),
		    		  user.getLastName(),
		    		  user.getRoleId(),
		    		  user.getStatus(),
		    		  date_format.format(dateobj),
		    		  user.getId() );
	    }
	       

	      
	 }
	

}
