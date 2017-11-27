package com.metrotraining.catalogus.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrotraining.catalogus.pojos.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public boolean existsByEmail(String email);

}
