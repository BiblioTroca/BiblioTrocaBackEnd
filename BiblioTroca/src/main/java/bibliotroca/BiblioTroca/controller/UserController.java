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
import bibliotroca.BiblioTroca.exception.EmailAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
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
	public UserDTO createUser(@RequestBody @Valid User user) throws EmailAlreadyInUseException {
		User userCreated = this.userService.createUser(user);
		UserDTO userDTO = UserDTO.returnUserDTO(userCreated);
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
	
	@GetMapping("/{email}")
	public UserDTO returnUserByEmail(@PathVariable String email) throws EmailNotFoundException {
		return UserDTO.returnUserDTO(userService.returnUserByEmail(email));
	}
	
	@GetMapping("/{email}/livros")
	public UserDTO returnUserWithBooksByEmail(@PathVariable String email) throws EmailNotFoundException, RegistryNotFoundException {
		User userRequest = this.userService.returnUserBooks(email);
		UserDTO userResponse = UserDTO.returnUserDTO(userRequest);
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book bookRequest : userRequest.getBooks()) {
			booksDTO.add(BookDTO.returnBookDTO(bookRequest));
		}
		userResponse.setBooks(booksDTO);
		return userResponse;
	}
	
	@PutMapping("/{email}/cadastrar-livro")
	public UserDTO updateUserBooksByEmail(@PathVariable String email, @RequestBody Book book) throws EmailNotFoundException, RegistryNotFoundException, StateNotValidException {
		this.bookService.createBook(book);
		User userRequest = this.userService.addBook(email, book.getRegistry());
		userRequest = this.userService.returnUserBooks(email);
		UserDTO userResponse = UserDTO.returnUserDTO(userRequest);
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book bookRequest : userRequest.getBooks()) {
			booksDTO.add(BookDTO.returnBookDTO(bookRequest));
		}
		userResponse.setBooks(booksDTO);
		return userResponse;
	}
	
	@PutMapping("/{email}")
	public UserDTO updateUserByEmail(@PathVariable String email, @RequestBody @Valid UserDTO userDTO) throws EmailNotFoundException {
		User userRequest = UserDTO.returnUser(userDTO);
		User userUpdated = this.userService.updateUser(email, userRequest);
		return UserDTO.returnUserDTO(userUpdated);
	}
	
	@DeleteMapping("/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String email) throws EmailNotFoundException {
		this.userService.deleteUser(email);
	}
}
