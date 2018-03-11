package com.sg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.repository.UserRepository;
import com.sg.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void insertUser(String name, String password) {
		userRepository.addNewUser(name, password);
	}

}
