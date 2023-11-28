package bibliotroca.BiblioTroca.service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.repository.BookRepository;
import bibliotroca.BiblioTroca.strategy.State;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	public Book createBook(Book book) {
		book.setRegistry(generateRegistry());
		book.setCreatedAt(LocalDateTime.now());
		return this.bookRepository.save(book);
	}

	public List<Book> returnAllBooks() {
		List<Book> bookList = this.bookRepository.findAll();
		bookList.removeIf(book -> book.getCreatedAt() == null); // Remover livros sem data de criação
		Collections.sort(bookList, (o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())); // Ordenar de forma reversa
		return bookList;
	}

	public Book returnBookByRegistry(Long registry) throws RegistryNotFoundException {
		if (!this.bookRepository.existsByRegistry(registry)) {
			throw new RegistryNotFoundException(registry);
		}
		return this.bookRepository.findByRegistry(registry);
	}

	public Book updateBook(Long registry, Book book) throws RegistryNotFoundException {
		if (!this.bookRepository.existsByRegistry(registry)) {
			throw new RegistryNotFoundException(registry);
		}
		Book bookRequest = this.bookRepository.findByRegistry(registry);
		book.setId(bookRequest.getId());
		book.setRegistry(registry);
		if (book.getTitle() == null) {
			book.setTitle(bookRequest.getTitle());
		}
		if (book.getAuthor() == null) {
			book.setAuthor(bookRequest.getAuthor());
		}
		if (book.getField() == null) {
			book.setField(bookRequest.getField());
		}
		if (book.getLanguage() == null) {
			book.setLanguage(bookRequest.getLanguage());
		}
		if (book.getEdition() == null) {
			book.setEdition(bookRequest.getEdition());
		}
		if (book.getDescription() == null) {
			book.setDescription(bookRequest.getDescription());
		}
		if (book.getPublishingCompany() == null) {
			book.setPublishingCompany(bookRequest.getPublishingCompany());
		}
		if (book.getState() == null) {
			book.setState(bookRequest.getState());
		}
		return this.bookRepository.save(book);
	}

	public void deleteBook(Long registry) throws RegistryNotFoundException {
		if (this.bookRepository.existsByRegistry(registry)) {
			this.bookRepository.deleteByRegistry(registry);
		} else {
			throw new RegistryNotFoundException(registry);
		}
	}

	private Long generateRegistry() {
		if (this.bookRepository.findAll().isEmpty()) {
			return (long) 1;
		}
		return (long) this.bookRepository.findAll().get(this.bookRepository.findAll().size() - 1).getRegistry() + 1;
	}

	protected Boolean existsByRegistry(Long registry) {
		if (this.bookRepository.existsByRegistry(registry)) {
			return true;
		}
		return false;
	}

	public List<Book> returnFilteredBooks(String search) {
		search.replace("+", " ");
		List<Book> books = new ArrayList<>();
		List<Book> filteredBooks = new ArrayList<>();
		books = this.returnAllBooks();
		for (Book book : books) {
			if (book.getTitle().toLowerCase().contains(search.toLowerCase()) ||
					book.getAuthor().toLowerCase().contains(search.toLowerCase()) ||
					book.getField().toLowerCase().contains(search.toLowerCase()) ||
					book.getDescription().toLowerCase().contains(search.toLowerCase()) ||
					book.getPublishingCompany().toLowerCase().contains(search.toLowerCase())) {
				filteredBooks.add(book);
			}
		}
		if (!filteredBooks.isEmpty()) {
			filteredBooks.removeIf(book -> book.getCreatedAt() == null);
			Collections.sort(filteredBooks, (o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
			return filteredBooks;
		}
		return new ArrayList<>();
	}

	public Book createBookFromCollectPoint() {
		Book book = new Book("Livro cadastrado pelo funcionário BiblioTroca",
				"Livro cadastrado pelo funcionário BiblioTroca",
				"Livro cadastrado pelo funcionário BiblioTroca", "Livro cadastrado pelo funcionário BiblioTroca",
				"Livro cadastrado pelo funcionário BiblioTroca", "Livro cadastrado pelo funcionário BiblioTroca",
				"Livro cadastrado pelo funcionário BiblioTroca", State.Seminovo);
		book.setRegistry(generateRegistry());
		return this.bookRepository.save(book);
	}
}
