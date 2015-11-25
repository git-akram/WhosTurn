package com.whoseturn.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.whoseturn.api.repository.UserRepository;
import com.whoseturn.api.entities.User;

/**
 * Service managing users.
 *
 */
@Named
public class UserService {
	
	@Inject
	private UserRepository userRepository;

	
	public List<User> find() {
		return userRepository.findAll();
	}
	
	public User find(Long id) {
		return userRepository.findOne(id);
	}
	
	public void persist(User user) {
		userRepository.save(user);
	}
}
