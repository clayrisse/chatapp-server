package com.chatapp;

import com.chatapp.dto.UChatterDTO;
import com.chatapp.model.Contact;
import com.chatapp.model.UAdmin;
import com.chatapp.model.UChatter;
import com.chatapp.model.User;
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

		userService.addChatter(new UChatterDTO("t1", "t1t1"));
		userService.addChatter(new UChatterDTO("t2", "t2t2"));
		UChatterDTO chatter= new UChatterDTO();
		chatter.setProfileName("Don Pepito Mogollon");
//		chatter.setUsername("t2");
		System.err.println(chatter.toString());
//		userService.updateChatter(1, chatter);





	}

}
