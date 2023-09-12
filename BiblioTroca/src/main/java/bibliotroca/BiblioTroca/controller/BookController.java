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
import bibliotroca.BiblioTroca.dto.BookDTO;
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
	public BookDTO createBook(@RequestBody @Valid Book book) {
		Book createdBook = this.bookService.createBook(book);
		BookDTO createdBookDTO = BookDTO.returnBookDTO(createdBook);
		createdBookDTO.setRegistry(book.getRegistry());
		return createdBookDTO;
	}
	
	@GetMapping
	public ArrayList<BookDTO> returnAllBooks() {
		List<Book> books = this.bookService.returnAllBooks();
		ArrayList<BookDTO> booksDTO = new ArrayList<>();
		for(Book book : books) {
			booksDTO.add(BookDTO.returnBookDTO(book));
		}
		return booksDTO;
	}
	
	@GetMapping("/{registry}")
	public BookDTO returnBookByRegistry(@PathVariable Long registry) throws RegistryNotFoundException {
		BookDTO book = BookDTO.returnBookDTO(bookService.returnBookByRegistry(registry));
		book.setRegistry(registry);
		return book;
	}
	
	@PutMapping("/{registry}")
	public BookDTO updateBook(@PathVariable Long registry, @RequestBody @Valid BookDTO bookDTO) throws RegistryNotFoundException {
		Book bookRequest = BookDTO.returnBook(bookDTO);
		Book bookUpdated = this.bookService.updateBook(registry, bookRequest);
		BookDTO book = BookDTO.returnBookDTO(bookUpdated);
		book.setRegistry(registry);
		return book;
	}
	
	@DeleteMapping("/{registry}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long registry) throws RegistryNotFoundException {
		this.bookService.deleteBook(registry);
	}
}
