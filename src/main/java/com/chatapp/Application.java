package com.chatapp;

import com.chatapp.model.Contact;
import com.chatapp.model.User;
import com.chatapp.repository.ContactRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
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
	@Autowired	ContactRepository contactRepository;

	@Override
	public void run(String... args) throws Exception {

		User u = new User ();
		System.err.println(u);
		User u1 = new User (38677,"username1","password1","name1", "profilepin1", LocalDateTime.now(),new ArrayList<>(),new ArrayList<>());
//		User u1 = new User (38677,"username1","password1","name1", "profilepin1");
		User u2 = new User ("name2","username2");
		User u3 = User.builder().profileName("name3").build();
//
		userRepository.saveAll(List.of(u, u1, u2, u3));
		u.setName("test");
		userRepository.save(u);
//		System.err.println("-------------------------------------" );
////		if (userRepository.findByUsername("name177").isPresent()) {
////			System.err.println("sssssssssssssssss");
////			User ux = userRepository.findByUsername(u1.getUsername()).get();
////			System.err.println(ux.getUsername());
////		}
////
		Contact c1 = Contact.builder().username("name177").customName("pepito117").build();
		Contact c2 = Contact.builder().username("username2").customName("pepito117").build();
//
		contactRepository.save(c1);
		contactRepository.save(c2);


	}

}
