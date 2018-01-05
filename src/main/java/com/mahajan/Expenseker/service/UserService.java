package com.mahajan.Expenseker.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	UserRepository userRepository;
	
	@Autowired
	UserExpenseRepository userExpenseRepository;
	
	@Autowired
	EntityManager em;
	
	

	public void addUser(User user) {
		userRepository.save(user);
	}

	public User getUser(int userId){
		return userRepository.findOne(userId);
	}

	public List<User> getAllUsers(){
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	public List<Group> getGroupsForUser(int id){
		User u   = userRepository.findOne(id);
		  Set<GroupUsers> userGroupsUsers = u.getGroupUsers();
		  List<Group> groupList = new ArrayList<Group>();
		  Iterator<GroupUsers> groupUsersIterator = userGroupsUsers.iterator();
		  while(groupUsersIterator.hasNext()){
			  groupList.add(groupUsersIterator.next().getGroup());
		  }
		return groupList;
	}

	public void addExpense(int userId, UserExpense expense) {
		
		User u = em.getReference(User.class, userId);
		expense.setUser(u);
		userExpenseRepository.save(expense);
	}

	public Set<UserExpense> getExpenses(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findOne(userId).getUserExpenses();
	}

	public void deleteUser(int id) {
		userRepository.delete(id);
		
	}

	public void deleteExpense(int id) {
		UserExpense uE = new UserExpense();
		uE.setExpenseId(id);
		userExpenseRepository.delete(uE);
//		sf = em.unwrap(SessionFactory.class);
//		sf.getCurrentSession().delete(uE);

		
	}
}
