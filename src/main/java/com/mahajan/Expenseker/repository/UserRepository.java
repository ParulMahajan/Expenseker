package com.mahajan.Expenseker.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahajan.Expenseker.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	//public void addUser(User user);
	
	public User findByUsername(String username);
	
	
}
