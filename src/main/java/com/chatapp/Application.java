package com.chatapp;

import com.chatapp.controller.MessageController;
import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.*;
import com.chatapp.repository.*;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired	UserRepository userRepository;
	@Autowired	RoleRepository roleRepository;
	@Autowired	UChatterRepository uChatterRepository;
	@Autowired	UAdminRepository uAdminRepository;
	@Autowired	ContactRepository contactRepository;
	@Autowired	UserService userService;
	@Autowired 	ChatRepository chatRepository;
	@Autowired	MessageController msgController;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) throws Exception {

	//---------------create type of users

//		User user = userRepository.save(new User("a","a"));
//		user.setRole("ADMIN");
//		userRepository.save(user);
//		System.err.println("........1");
//		UAdmin admin = UAdmin.builder().username("admonistrrator").password("dsdvsvsd").build();
//		admin.setRole("ADMIN");
//		uAdminRepository.save(admin);
//		System.err.println("........2");
//		UChatter uc1 = new UChatter("pepi", "pepi");
//		System.err.println(uc1.getLastSeen());
//		System.err.println("........3");
//		uChatterRepository.save(uc1);
		System.err.println("........................................you made a user, uChatter and a uAdmin alright");

	//-------------------------------------------users

		userService.addChatter(new UChatterDTO("q1", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q2", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q3", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q4", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q5", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q6", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q7", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q8", "qqqqqqq"));
		userService.addChatter(new UChatterDTO("q9", "qqqqqqq"));


	}

}
