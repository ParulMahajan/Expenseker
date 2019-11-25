package com.mahajan.Expenseker.controller;


import java.util.List;
import java.util.Set;

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
import com.mahajan.Expenseker.model.User;
import com.mahajan.Expenseker.model.UserExpense;
import com.mahajan.Expenseker.response.RestCallResponse;
import com.mahajan.Expenseker.service.UserService;

@Path("user")
@Component
public class UserController {

	@Autowired
	UserService userService;

	@Path("addUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		System.out.println(user);
		System.out.println(user);
		
		userService.addUser(user);
		return Response.status(201).entity(new RestCallResponse("User")).build();
	}

	@Path("getUser/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userId") int userId){

		User user =	userService.getUser(userId);
		return Response.status(200).entity(user).build();
	}

	@Path("getUsers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){

		List<User> users =	userService.getAllUsers();
		return Response.status(200).entity(users).build();
	}

	@Path("/{userId}/addExpense")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExpense(@PathParam("userId") int userId, UserExpense userExpense){
		
		userService.addExpense(userId, userExpense);
		return Response.status(201).entity(new RestCallResponse("Expense")).build();
	}
	
	@Path("getExpenses/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExpenses(@PathParam("userId") int userId){
		
		Set<UserExpense> expenses = userService.getExpenses(userId);
		return Response.status(200).entity(expenses).build();
	}
	
	
	@Path("getGroups/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupsForUser(@PathParam("id") int id){

		List<Group> userGroups =	userService.getGroupsForUser(id);
		return Response.status(200).entity(userGroups).build();
	}
	
	public static void main(String[] args) {
		System.out.println("aspsd".substring(0, 3));
	}
}
