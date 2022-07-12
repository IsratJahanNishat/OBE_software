package com.dev.FinalProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.FinalProject.entity.LoginUser;


public interface LoginRepo extends JpaRepository<LoginUser,Long>{ 
	
	LoginUser findByUserId(String userId);

}
