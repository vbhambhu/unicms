package com.unicms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.unicms.models.Post;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	public Post findAll() {
		
		return null;
	}
	
	public List<Post> listPost() {
		
		String SQL = "SELECT id,title,content FROM posts";
		List <Post> posts = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<Post>(Post.class));
		return posts;
	}


	
	public List<Post> listCategories() {
		
	      String SQL = "SELECT * FROM categories";
	      
	      List<Post> post_categories = jdbcTemplate.query( SQL, new BeanPropertyRowMapper<Post>(Post.class));
	      
	      return post_categories;
	}
	
	public int slugCount(String slug) {
		
		String SQL = "SELECT count(*) FROM posts WHERE slug = ?";
		
		return jdbcTemplate.queryForObject(SQL, new Object[] { slug }, Integer.class);
		
	}
	
	public void createPost(Post post) {
		
		   DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Date dateobj = new Date();
	       
		   String SQL = "INSERT INTO posts (title,slug,content,category_id,status,created_at) values (?,?,?,?,?,?)";
		   
		   jdbcTemplate.update(SQL, new Object[] { 
				  post.getTitle(),
				  post.getSlug(),
				  post.getContent(),
				  post.getCategoryId(),
				  post.isStatus(),
				  date_format.format(dateobj) });
		
	}

	
	
	
	
}
