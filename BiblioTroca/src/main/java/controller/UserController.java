package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exception.CpfNotFoundException;

@RestController
@RequestMapping("/api/v1/bibliotroca/usuarios")
public class UserController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createNewUser(@Valid @RequestBody User user) throws CpfAlreadyInUseException {
		
	}
	
	@GetMapping
	public List<User> returnAllUsers(){
		return this.userService.returnAllUsers();
	}
	
	@GetMapping("/{cpf}")
	public User returnUserByCPF(@PathVariable String cpf) throws CpfNotFoundException {
		return this.userService.returnUserByCPF(cpf);
	}
}
