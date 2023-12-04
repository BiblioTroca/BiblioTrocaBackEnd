package bibliotroca.BiblioTroca.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.service.BookService;
import bibliotroca.BiblioTroca.service.UserService;

@RestController
@RequestMapping("/api/v1/bibliotroca/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
	
	// USER ENDPOINTS
	
	@GetMapping("/usuarios")
	public List<User> returnAllUsers(){
		return this.userService.returnAllUsers();
	}
	
	@GetMapping("/usuarios/{email}")
	public User returnUserByCPF(@PathVariable String email) throws EmailNotFoundException {
		return userService.returnUserByEmail(email);
	}
	
	@GetMapping("/usuarios/{email}/livros")
	public User returnUserWithBooksByCPF(@PathVariable String email) throws EmailNotFoundException, RegistryNotFoundException {
		User userRequest = this.userService.returnUserBooks(email);
		List<Book> books = new ArrayList<>();
		for(Book bookRequest : userRequest.getBooks()) {
			books.add(bookRequest);
		}
		userRequest.setBooks(books);
		return userRequest;
	}
	
	
	// BOOKS ENDPOINTS
	
	@GetMapping("/livros")
	public List<Book> returnAllBooks() {
		return this.bookService.returnAllBooks();
	}
	
	@GetMapping("/livros/{registry}")
	public Book returnBookByRegistry(@PathVariable Long registry) throws RegistryNotFoundException {
		return bookService.returnBookByRegistry(registry);
	}
}
