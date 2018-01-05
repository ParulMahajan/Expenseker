package com.mahajan.Expenseker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="GroupUsersExpense")
public class GroupUsersExpense {

	@Id
	@GeneratedValue
	private int expenseId;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="groupUsersId")
	private GroupUsers groupUsers;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="groupId")
//	private Group group;
//	
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="userId")
//	private User user;

	private double amount;
	private String description;
	
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
//	public Group getGroup() {
//		return group;
//	}
//	public void setGroup(Group group) {
//		this.group = group;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
