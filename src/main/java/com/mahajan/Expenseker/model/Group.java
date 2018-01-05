package com.mahajan.Expenseker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group{

	@Id
	@GeneratedValue
	private int groupId;
	private String groupName;

	@OneToMany(mappedBy="group",fetch=FetchType.EAGER)
	private Set<GroupUsers> groupUsers;
	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="group")
//	private Set<GroupExpense> groupExpenses;

	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
//	public Set<GroupExpense> getGroupExpenses() {
//		return groupExpenses;
//	}
//	public void setGroupExpenses(Set<GroupExpense> groupExpenses) {
//		this.groupExpenses = groupExpenses;
//	}
	public Set<GroupUsers> getGroupUsers() {
		return groupUsers;
	}
	public void setGroupUsers(Set<GroupUsers> groupUsers) {
		this.groupUsers = groupUsers;
	}








}
