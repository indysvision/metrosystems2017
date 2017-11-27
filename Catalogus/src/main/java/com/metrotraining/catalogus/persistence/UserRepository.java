package com.metrotraining.catalogus.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.pojos.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public List<User> findByType(UserRole type);
	
	public User findById(long id);

}
