package com.metrotraining.catalogus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.UserRepository;

@Service
@Transactional
public class UserService {
	
	private final UserRepository userRepo;
	public UserService(UserRepository repo) {
		userRepo = repo;
	}

	public void save(User part) {
		userRepo.save(part);
	}
	
	public void deleteUserPart(long id) {
		userRepo.delete(userRepo.findById(id));
		System.out.println("delete "+id);
	}
	
	public User getUser(long id) {
		return userRepo.findById(id);
	}
}

