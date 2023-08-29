package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import entity.User;
import exception.CpfAlreadyInUseException;
import exception.CpfNotFoundException;
import jakarta.validation.Valid;
import service.UserService;

@RestController
@RequestMapping("/api/v1/bibliotroca/usuarios")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createNewUser(@Valid @RequestBody User user) throws CpfAlreadyInUseException {
		return this.userService.createNewUser(user);
	}
	
	@GetMapping
	public List<User> returnAllUsers(){
		return this.userService.returnAllUsers();
	}
	
	@GetMapping("/{cpf}")
	public User returnUserByCPF(@PathVariable String cpf) throws CpfNotFoundException {
		return this.userService.returnUserByCPF(cpf);
	}
	
	@PutMapping("/{cpf}")
	public UserDTO updateUserByCPF(@PathVariable String cpf, @Valid UserDTO userDTO) throws CpfNotFoundException {
		User userRequest = UserDTO.returnUser(userDTO);
		User userUpdated = this.userService.updateUser(cpf, userRequest);
		return UserDTO.returnUserDTO(userUpdated);
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String cpf) throws CpfNotFoundException {
		this.userService.deleteUser(cpf);
	}
}
