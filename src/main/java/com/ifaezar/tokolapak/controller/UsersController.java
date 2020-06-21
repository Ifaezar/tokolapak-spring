package com.ifaezar.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.UserRepo;
import com.ifaezar.tokolapak.entity.User;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
	@Autowired
	private UserRepo userRepo;
	
	private PasswordEncoder pwEncoder= new BCryptPasswordEncoder();
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
//		Optional <User> findUser = userRepo.findByUsername(user.getUsername());
		String encodedPassword = pwEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}
	
	//cara pertama hash passworrd
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User findUser = userRepo.findByUsername(user.getUsername()).get();
		
		if(pwEncoder.matches(user.getPassword(), findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		}else {
			return null;
		}
	}
	
	//cara kedua hash passworrd(rekomendasi)
	@GetMapping("/login")
	public User getLoginUser(@RequestParam String username, @RequestParam String password) {
		User findUser = userRepo.findByUsername(username).get();
		
		if(pwEncoder.matches(password, findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		}else {
			return null;
		}
	}
}
