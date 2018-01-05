package com.mahajan.Expenseker.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mahajan.Expenseker.model.Group;
import com.mahajan.Expenseker.model.GroupUsers;
import com.mahajan.Expenseker.model.User;
import com.mahajan.Expenseker.repository.GroupRepository;
import com.mahajan.Expenseker.repository.GroupUsersRepository;

@Service
public class GroupService {

	List<String> groupAmountSummary; 
	
	@Autowired
	GroupRepository groupRepository;

	@Autowired
	EntityManager em;
		
	@Autowired
	DataSource source;

	@Autowired
	GroupUsersRepository groupUsersRepository;

	public Group addGroup(Group group){
		Group savedGroup =	groupRepository.save(group);

		return savedGroup;
	}

	public Group getGroup(int id){
		Group group =	groupRepository.findOne(id);

		return group;
	}

	public void addUser(int groupId, int userId) {

		User u = em.getReference(User.class, userId);
		Group g = em.getReference(Group.class, groupId);
		
		GroupUsers gU = new GroupUsers();
		gU.setUser(u);gU.setGroup(g);
		
		groupUsersRepository.save(gU);
	}

	public List<String>  getSummary(int groupId) {

		groupAmountSummary = new ArrayList<String>();
		
		Group group = groupRepository.findOne(groupId);

		Set<GroupUsers> groupUsers = group.getGroupUsers();
         int totalUsersinGroup = groupUsers.size();
        		 
		Map<String,Double> userTotalExpenses = new HashMap<String,Double>();

		Double toatlGroupExpenses = 0.0;

		for(GroupUsers groupUser: groupUsers){
			toatlGroupExpenses = toatlGroupExpenses+groupUser.getTotalExpense();
		}

		for(GroupUsers groupUser: groupUsers){
			userTotalExpenses.put(groupUser.getUser().getUserId()+"_"+groupUser.getUser().getUsername(), (groupUser.getTotalExpense() -(toatlGroupExpenses/totalUsersinGroup)  ));
		}

		splitExpenses(userTotalExpenses, groupAmountSummary);
		return groupAmountSummary;
	}

	private  List<String> splitExpenses(Map<String,Double> userTotalExpenses, List<String> groupAmountSummary){

				
		Double max_value =  Collections.max(userTotalExpenses.values());
		Double min_value =  Collections.min(userTotalExpenses.values());

		if(max_value!=min_value){ //if not settled up

			String max_key = getMaxKey(userTotalExpenses, max_value);
			String min_key = getMinKey(userTotalExpenses, min_value);
			
			Double result = result(max_value+min_value);
			
			if( result >= 0.0){
				
				groupAmountSummary.add(min_key + " needs to pay "+ max_key+" : "+ result(Math.abs(min_value)));
				
				userTotalExpenses.remove(max_key);
				userTotalExpenses.remove(min_key);
				userTotalExpenses.put(max_key, result);
				userTotalExpenses.put(min_key, 0.0);
				
			}else{
				groupAmountSummary.add(min_key + " needs to pay "+ max_key+" : "+ result(Math.abs(max_value)));
				userTotalExpenses.remove(max_key);
				userTotalExpenses.remove(min_key);
				userTotalExpenses.put(max_key, 0.0);
				userTotalExpenses.put(min_key, result);
			}
				
			splitExpenses(userTotalExpenses, groupAmountSummary);
		}// All split is done
		
		
		return groupAmountSummary;
	}

	

	private  String getMaxKey(Map<String, Double> userTotalExpenses, Double max_value) {

		String max_key = null;
		for(Map.Entry<String, Double> entry : userTotalExpenses.entrySet()){
			if(entry.getValue()==max_value){
				max_key = entry.getKey();
			}
		}
		return max_key;
	}

	private  String getMinKey(Map<String, Double> userTotalExpenses, Double min_value) {

		String min_key = null;
		for(Map.Entry<String, Double> entry : userTotalExpenses.entrySet()){
			if(entry.getValue()==min_value){
				min_key = entry.getKey();
			}
		}
		return min_key;
	}
	
	private  Double result(Double value) {
	
		BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
		
	}
}
