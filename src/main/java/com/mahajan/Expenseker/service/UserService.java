package com.mahajan.Expenseker.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mahajan.Expenseker.model.Group;
import com.mahajan.Expenseker.model.GroupUsers;
import com.mahajan.Expenseker.model.User;
import com.mahajan.Expenseker.model.UserExpense;
import com.mahajan.Expenseker.repository.UserExpenseRepository;
import com.mahajan.Expenseker.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserExpenseRepository userExpenseRepository;

	public void addUser(User user) {
		userRepository.save(user);
	}

	public User getUser(int userId){
		return userRepository.findById(userId).get();
	}

	public List<User> getAllUsers(){
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	public List<Group> getGroupsForUser(int id){
		  Set<GroupUsers> userGroupsUsers = userRepository.findById(id).get().getGroupUsers();
		  
		  List<Group> groupList = new ArrayList<Group>();
		  Iterator<GroupUsers> groupUsersIterator = userGroupsUsers.iterator();
		  while(groupUsersIterator.hasNext()){
			  groupList.add(groupUsersIterator.next().getGroup());
		  }
		return groupList;
	}

	public void addExpense(int userId, UserExpense expense) {
		
		String query = "INSERT INTO USER_EXPENSE VALUES (HIBERNATE_SEQUENCE.nextval,"+expense.getAmount()+",'"+expense.getDescription()+"',"+userId+")";
		jdbcTemplate.execute(query);
	}

	public Set<UserExpense> getExpenses(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).get().getUserExpenses();
	}
}
