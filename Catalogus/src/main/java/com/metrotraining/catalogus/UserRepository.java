package com.metrotraining.catalogus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metrotraining.catalogus.pojos.User;

public interface UserRepository extends JpaRepository<User,Long> {
	public User findById(long id);
}
