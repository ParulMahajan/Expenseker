package com.mahajan.Expenseker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Group_users")
public class GroupUsers  {

	@Id
	@GeneratedValue
	@JsonIgnore
	private int groupUsersId;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userId")  
	private User user;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "groupId") 
	@JsonIgnore
	private Group group;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="groupUsers") 
	private Set<GroupUsersExpense> groupUsersExpense;

	@Column(nullable=true)
	private Double totalExpense;
	
	public int getGroupUsersId() {
		return groupUsersId;
	}

	public void setGroupUsersId(int groupUsersId) {
		this.groupUsersId = groupUsersId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Set<GroupUsersExpense> getGroupUsersExpense() {
		return groupUsersExpense;
	}

	public void setGroupUsersExpense(Set<GroupUsersExpense> groupUsersExpense) {
		this.groupUsersExpense = groupUsersExpense;
	}

	public Double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}
	
	
}
