package com.ifaezar.tokolapak.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class HelloController {
	@GetMapping("/Hello")
	public String helloWorld() {
		return "Hello";
	}
		
	@GetMapping("/Hello/{name}")
	public String helloName(@PathVariable() String name) {
		return "Hello" + name;
	}
	
	private PasswordEncoder pweEncoder= new BCryptPasswordEncoder();
}
