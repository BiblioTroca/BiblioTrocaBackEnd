package bibliotroca.BiblioTroca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public Book createBook(Book book) {
		book.setRegistry(generateRegistry());
		return this.bookRepository.save(book);
	}
	
	public List<Book> returnAllBooks() {
		return this.bookRepository.findAll();
	}
	
	public Book returnBookByRegistry(Long registry) throws RegistryNotFoundException {
		if(!this.bookRepository.existsByRegistry(registry)) {
			throw new RegistryNotFoundException(registry);
		}
		return this.bookRepository.findByRegistry(registry);
	}
	
	public Book updateBook(Long registry, Book book) throws RegistryNotFoundException {
		if(!this.bookRepository.existsByRegistry(registry)) {
			throw new RegistryNotFoundException(registry);
		}
		Book bookRequest = this.bookRepository.findByRegistry(registry);
		book.setId(bookRequest.getId());
		book.setRegistry(registry);
		if(book.getTitle() == null) {
			book.setTitle(bookRequest.getTitle());
		}
		if(book.getAuthor() == null) {
			book.setAuthor(bookRequest.getAuthor());
		}
		if(book.getField() == null) {
			book.setField(bookRequest.getField());
		}
		if(book.getLanguage() == null) {
			book.setLanguage(bookRequest.getLanguage());
		}
		if(book.getEdition() == null) {
			book.setEdition(bookRequest.getEdition());
		}
		if(book.getDescription() == null) {
			book.setDescription(bookRequest.getDescription());
		}
		if(book.getPublishingCompany() == null) {
			book.setPublishingCompany(bookRequest.getPublishingCompany());
		}
		if(book.getState() == null) {
			book.setState(bookRequest.getState());
		}
		return this.bookRepository.save(book);
	}
	
	public void deleteBook(Long registry) throws RegistryNotFoundException {
		if(this.bookRepository.existsByRegistry(registry)) {
			this.bookRepository.deleteByRegistry(registry);
		} else {
			throw new RegistryNotFoundException(registry);
		}
	}
	
	private Long generateRegistry() {
		if(returnAllBooks().isEmpty()) {
			return (long) 1;
		}
		return (long) returnAllBooks().get(returnAllBooks().size()-1).getRegistry() + 1;
	}
	
	protected Boolean existsByRegistry(Long registry) {
		if(this.bookRepository.existsByRegistry(registry)) {
			return true;
		}
		return false;
	}
}
