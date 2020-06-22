package com.ifaezar.tokolapak.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifaezar.tokolapak.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);
}
