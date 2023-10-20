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
import bibliotroca.BiblioTroca.dto.BookDTO;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.exception.StateNotValidException;
import bibliotroca.BiblioTroca.service.BookService;
import bibliotroca.BiblioTroca.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/usuarios")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
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
	
	@GetMapping("/{cpf}/livros")
	public UserDTO returnUserWithBooksByCPF(@PathVariable String cpf) throws CpfNotFoundException, RegistryNotFoundException {
		User userRequest = this.userService.returnUserBooks(cpf);
		UserDTO userResponse = UserDTO.returnUserDTO(userRequest);
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book bookRequest : userRequest.getBooks()) {
			booksDTO.add(BookDTO.returnBookDTO(bookRequest));
		}
		userResponse.setCpf(cpf);
		userResponse.setBooks(booksDTO);
		return userResponse;
	}
	
	@PutMapping("/{cpf}/cadastrar-livro")
	public UserDTO updateUserBooksByCPF(@PathVariable String cpf, @RequestBody Book book) throws CpfNotFoundException, RegistryNotFoundException, StateNotValidException {
		this.bookService.createBook(book);
		User userRequest = this.userService.addBook(cpf, book.getRegistry());
		userRequest = this.userService.returnUserBooks(cpf);
		UserDTO userResponse = UserDTO.returnUserDTO(userRequest);
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book bookRequest : userRequest.getBooks()) {
			booksDTO.add(BookDTO.returnBookDTO(bookRequest));
		}
		userResponse.setBooks(booksDTO);
		return userResponse;
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
