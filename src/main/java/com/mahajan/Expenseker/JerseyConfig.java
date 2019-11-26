package com.mahajan.Expenseker;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.mahajan.Expenseker.controller.UserController;

@Component
public class JerseyConfig extends ResourceConfig{

	
	public JerseyConfig() {
		// packages("com.mahajan.Expenseker.controller");
		register(com.mahajan.Expenseker.controller.UserController.class);
		register(com.mahajan.Expenseker.controller.GroupController.class);
	}
}
