package com.ifaezar.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.UserRepo;
import com.ifaezar.tokolapak.entity.User;
import com.ifaezar.tokolapak.util.EmailUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
	@Autowired
	private UserRepo userRepo;
	
	private PasswordEncoder pwEncoder= new BCryptPasswordEncoder();
	

	
	@Autowired
	private EmailUtil emailUtil;
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
//		Optional <User> findUser = userRepo.findByUsername(user.getUsername());
		String encodedPassword = pwEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		this.emailUtil.sendEmail(user.getEmail(), "Testing", "<h1>Silahkan klik <a href = \"http://localhost:8080/users/sukses/"+user.getEmail()+"\">Link<a> untuk verifikasi</h1>");
		
		return userRepo.save(user);
	}
	
	@GetMapping("/sukses/{email}")
	public String loginSuccess( @PathVariable String email) {
		
		User findEmail = userRepo.findByEmail(email).get();
		findEmail.setIsVerified("verified");
		userRepo.save(findEmail);
		return "Selamat Email anda sudah terverifikasi" ;
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
	
	@PostMapping("/sendEmail")
	public String sendEmail() {
		this.emailUtil.sendEmail("faezarilham@gmail.com", "Testing Spring Email", "<h1>Halo<h1>");
		return "Email Sent";
	}
	
}
