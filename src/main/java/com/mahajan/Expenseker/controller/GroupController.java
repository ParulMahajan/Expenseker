package com.mahajan.Expenseker.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mahajan.Expenseker.model.Group;
import com.mahajan.Expenseker.model.GroupUsersExpense;
import com.mahajan.Expenseker.response.RestCallResponse;
import com.mahajan.Expenseker.service.GroupService;
import com.mahajan.Expenseker.service.GroupUsersExpenseService;

@Path("group")
@Component
public class GroupController {

	@Autowired
	GroupService groupService;
	
	@Autowired
	GroupUsersExpenseService groupUsersExpenseService;

	@Path("addGroup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newGroup(Group group){
		Group savedGroup = groupService.addGroup(group);
		return Response.status(201).entity(savedGroup).build();
	}

	@Path("/{groupId}/addUser/{userId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@PathParam("groupId") int groupId, @PathParam("userId") int userId){
		groupService.addUser(groupId, userId);
		return Response.status(201).entity(new RestCallResponse("User in group")).build();
	}

	@Path("/{groupUsersId}/addExpense")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroupExpense(@PathParam("groupUsersId") int groupUsersId,GroupUsersExpense groupUsersExpense){
		groupUsersExpenseService.addGroupExpense(groupUsersExpense, groupUsersId);
		return Response.status(201).entity(new RestCallResponse("Expense in group")).build();
	}

	@Path("getGroup/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroup(@PathParam(value = "id") int id){
		Group group=groupService.getGroup(id);

		return Response.status(200).entity(group).build();
	}
	
	@Path("summary/{groupId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSummary(@PathParam("groupId") int groupId){
		List<String> groupAmountSummary = 	groupService.getSummary(groupId);

		return Response.status(200).entity(groupAmountSummary).build();
	}
}
