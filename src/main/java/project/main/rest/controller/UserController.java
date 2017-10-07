package project.main.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import project.main.entity.User;
import project.main.repository.UserRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {

	@Autowired
	private UserRepository repository;

	@RequestMapping(path = "/users/username={username}&password={password}", method = RequestMethod.GET)
	ResponseEntity<?> findUserByNameAndPassword(@PathVariable String username, @PathVariable String password) {
		User user = repository.findByUserAndPassword(username, password);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);	
		}		
	}
	
	@RequestMapping(path = "/users/username={username}&password={password}/facebook", method = RequestMethod.POST)
	ResponseEntity<?> findUserByNameAndPasswordOrCreate(@PathVariable String username, @PathVariable String password) {
		User user = repository.findByUserAndPassword(username, password);
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			repository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}		
	}
	
	@RequestMapping(path = "/facebook", method = RequestMethod.POST)
	ResponseEntity<?> findUserByNameAndPasswordOrCreate(@RequestBody ObjectNode field) {
		String username = field.get("username").asText();
		String password = field.get("password").asText();
		String name = field.get("name").asText();
		User user = repository.findByUserAndPassword(username, password);
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			repository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}		
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	ResponseEntity<?> UserByNameAndPassword(@RequestBody ObjectNode field) {
		String username = field.get("username").asText();
		String password = field.get("password").asText();
		User user = repository.findByUserAndPassword(username, password);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);	
		}		
	}
}
