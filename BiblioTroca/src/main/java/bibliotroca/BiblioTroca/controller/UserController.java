package bibliotroca.BiblioTroca.controller;

import java.util.ArrayList;
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
import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/usuarios")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@RequestBody @Valid User user) throws CpfAlreadyInUseException {
		User userCreated = this.userService.createUser(user);
		UserDTO userDTO = UserDTO.returnUserDTO(userCreated);
		userDTO.setCpf(user.getCpf());
		return userDTO;
	}

	@GetMapping
	public ArrayList<UserDTO> returnAllUsers(){
		List<User> users = this.userService.returnAllUsers();
		ArrayList<UserDTO> usersDTO = new ArrayList<>();
		for(User user : users) {
			usersDTO.add(UserDTO.returnUserDTO(user));
		}
		return usersDTO;
	}
	
	@GetMapping("/{cpf}")
	public UserDTO returnUserByCPF(@PathVariable String cpf) throws CpfNotFoundException {
		UserDTO user = UserDTO.returnUserDTO(userService.returnUserByCPF(cpf));
		user.setCpf(cpf);
		return user;
	}
	
	@PutMapping("/{cpf}")
	public UserDTO updateUserByCPF(@PathVariable String cpf, @RequestBody @Valid UserDTO userDTO) throws CpfNotFoundException {
		User userRequest = UserDTO.returnUser(userDTO);
		User userUpdated = this.userService.updateUser(cpf, userRequest);
		UserDTO user = UserDTO.returnUserDTO(userUpdated);
		user.setCpf(cpf);
		return user;
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String cpf) throws CpfNotFoundException {
		this.userService.deleteUser(cpf);
	}
}
