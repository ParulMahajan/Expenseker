package com.mahajan.Expenseker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mahajan.Expenseker.model.GroupUsersExpense;
import com.mahajan.Expenseker.repository.GroupUsersExpenseRepository;

@Service
public class GroupUsersExpenseService {

	@Autowired
	GroupUsersExpenseRepository goupUsersExpenseRepository;
	
	@Autowired
	JdbcTemplate jdbcTemaplte;
	
	public void addGroupExpense(GroupUsersExpense groupUsersExpense, int groupUsersId) {
		
		String insertExpense = "INSERT INTO Group_Users_Expense VALUES (HIBERNATE_SEQUENCE.nextval,"+groupUsersExpense.getAmount()
		+",'"+groupUsersExpense.getDescription()+"',"+groupUsersId+")";
		jdbcTemaplte.execute(insertExpense);
		
		//Update Amount value
		String getOldAmountQuery = "SELECT TOTAL_EXPENSE from Group_Users  WHERE GROUP_USERS_ID = "+groupUsersId;
		Integer oldAmount = jdbcTemaplte.queryForObject(getOldAmountQuery, Integer.class);
		if(oldAmount!=null){
		groupUsersExpense.setAmount(oldAmount+groupUsersExpense.getAmount());
		}
		
		//Update amount in  Group_Users table
		String updateUserExpense= "UPDATE Group_Users SET TOTAL_EXPENSE = "+ groupUsersExpense.getAmount()+
				" WHERE GROUP_USERS_ID = "+groupUsersId;
		jdbcTemaplte.execute(updateUserExpense);
	}
}
