package com.luan.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.luan.clients.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public UserDetails findByUsername(String username);
    
}
