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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import bibliotroca.BiblioTroca.dto.BookDTO;
import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.service.BookService;
import bibliotroca.BiblioTroca.service.UserBooksService;
import bibliotroca.BiblioTroca.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/livros")
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	UserService userService;
	@Autowired
	UserBooksService userBooksService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO createBook(@RequestBody @Valid Book book) throws EmailNotFoundException {
		Book createdBook = this.bookService.createBook(book);
		this.userBooksService.addUserBook(createdBook.getUserEmail(), createdBook.getRegistry());
		BookDTO createdBookDTO = BookDTO.returnBookDTO(createdBook);
		createdBookDTO.setRegistry(book.getRegistry());
		UserDTO user = UserDTO.returnUserDTO(this.userService.returnUserByEmail(createdBook.getUserEmail()));
		user.setEmail(createdBook.getUserEmail());
		createdBookDTO.setUser(user);
		return createdBookDTO;
	}
	
	@GetMapping
	public ArrayList<BookDTO> returnAllBooks(@RequestParam(required=false) String q) throws EmailNotFoundException {
		List<Book> books = new ArrayList<>();
		if(q == null) {
			books = this.bookService.returnAllBooks();
		} else {
			books = this.bookService.returnFilteredBooks(q);
		}
		ArrayList<BookDTO> booksDTO = new ArrayList<>();
		for(Book book : books) {
			BookDTO bookDTO = BookDTO.returnBookDTO(book);
			UserDTO user = UserDTO.returnUserDTO(this.userService.returnUserByEmail(book.getUserEmail()));
			user.setEmail(book.getUserEmail());
			bookDTO.setUser(user);
			booksDTO.add(bookDTO);
		}
		return booksDTO;
	}
	
	@GetMapping("/{registry}")
	public BookDTO returnBookByRegistry(@PathVariable Long registry) throws RegistryNotFoundException, EmailNotFoundException {
		Book book = this.bookService.returnBookByRegistry(registry);
		BookDTO bookDTO = BookDTO.returnBookDTO(book);
		UserDTO user = UserDTO.returnUserDTO(this.userService.returnUserByEmail(book.getUserEmail()));
		user.setEmail(book.getUserEmail());
		bookDTO.setUser(user);
		bookDTO.setRegistry(registry);	
		return bookDTO;
	}
	
	@PutMapping("/{registry}")
	public BookDTO updateBook(@PathVariable Long registry, @RequestBody @Valid Book book) throws RegistryNotFoundException, EmailNotFoundException {
		Book bookUpdated = this.bookService.updateBook(registry, book);
		BookDTO bookDTO = BookDTO.returnBookDTO(bookUpdated);
		bookDTO.setRegistry(registry);
		UserDTO user = UserDTO.returnUserDTO(this.userService.returnUserByEmail(bookUpdated.getUserEmail()));
		user.setEmail(bookUpdated.getUserEmail());
		bookDTO.setUser(user);
		return bookDTO;
	}
	
	@DeleteMapping("/{registry}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long registry) throws RegistryNotFoundException {
		this.bookService.deleteBook(registry);
	}
	
	@GetMapping("/ponto-de-coleta")
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO createBookFromCollectPoint() {
		Book createdBook = this.bookService.createBookFromCollectPoint();
		BookDTO createdBookDTO = BookDTO.returnBookDTO(createdBook);
		createdBookDTO.setRegistry(createdBook.getRegistry());
		return createdBookDTO;
	}
}
