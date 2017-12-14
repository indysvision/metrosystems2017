package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontajRepository extends JpaRepository<Pontaj, Long> {

	public ArrayList<Pontaj> findAll();
	public Pontaj findById(long id);
	//public boolean existsByEmailAndPassword(String email,String password);
	//public User  findByEmail(String email);
	//public User findByEmailAndPassword(String email,String password);
	//public List<User> findByType(UserRole type);
	
}
