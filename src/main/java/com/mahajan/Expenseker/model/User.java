package com.mahajan.Expenseker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	private int userId;
	private String username;
	private String email;
	private String mobile;

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<GroupUsers> groupUsers;

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<UserExpense> userExpenses;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Set<GroupUsers> getGroupUsers() {
		return groupUsers;
	}
	public void setGroupUsers(Set<GroupUsers> groupUsers) {
		this.groupUsers = groupUsers;
	}
	public Set<UserExpense> getUserExpenses() {
		return userExpenses;
	}
	public void setUserExpenses(Set<UserExpense> userExpenses) {
		this.userExpenses = userExpenses;
	}






}
