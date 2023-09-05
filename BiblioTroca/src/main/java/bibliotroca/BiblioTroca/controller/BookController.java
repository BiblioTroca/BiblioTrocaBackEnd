package bibliotroca.BiblioTroca.controller;

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
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/livros")
public class BookController {
	@Autowired
	BookService bookService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody @Valid Book book) {
		return this.bookService.createBook(book);
	}
	
	@GetMapping
	public List<Book> returnAllBooks() {
		return this.bookService.returnAllBooks();
	}
	
	@GetMapping("/{registry}")
	public Book returnBookByRegistry(@PathVariable Long registry) throws RegistryNotFoundException {
		return this.bookService.returnBookByRegistry(registry);
	}
	
	@PutMapping("/{registry}")
	public Book updateBook(@PathVariable Long registry, @RequestBody @Valid Book book) throws RegistryNotFoundException {
		return this.bookService.updateBook(registry, book);
	}
	
	@DeleteMapping("/{registry}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long registry) throws RegistryNotFoundException {
		this.bookService.deleteBook(registry);
	}
}
