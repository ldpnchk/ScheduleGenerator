package com.sg.controller.restApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sg.entity.User;
import com.sg.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "createUser", method = RequestMethod.POST, produces = "application/json")
	public int createUser(@RequestBody User jUser){
		System.out.println("trying adding new user");
		try {
			userService.insertUser(jUser.getName(), jUser.getPassword());
			System.out.println("new user added");
			return 1;
		// Such username is already exists or DB connection error. 
		}catch(Exception e) {
			System.out.println("error");
			return 0;
		}
	}

}
