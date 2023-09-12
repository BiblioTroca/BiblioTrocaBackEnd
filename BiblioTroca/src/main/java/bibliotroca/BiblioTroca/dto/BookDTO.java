package bibliotroca.BiblioTroca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.strategy.State;

public class BookDTO {
	@JsonInclude(Include.NON_NULL)
	private Long registry;
	private String title;
	private String author;
	private String field;
	private String language;
	private String edition;
	private String description;
	private String publishingCompany;
	private State state;
	
	public BookDTO( ) {	}
	
	public BookDTO(String newTitle, String nweAuthor, String newField, String newLanguage, String newEdition, String newDescription, String newPublishingCompany, State newState) {
		this.title = newTitle;
		this.author = nweAuthor;
		this.field = newField;
		this.language = newLanguage;
		this.edition = newEdition;
		this.description = newDescription;
		this.publishingCompany = newPublishingCompany;
		this.state = newState;
	}

	public Long getRegistry() {
		return registry;
	}
	public void setRegistry(Long registry) {
		this.registry = registry;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public static BookDTO returnBookDTO(Book book) {
		return new BookDTO(book.getTitle(), book.getAuthor(), book.getField(), book.getLanguage(), book.getEdition(), book.getDescription(), book.getPublishingCompany(), book.getState());
	}
	
	public static Book returnBook(BookDTO bookDTO) {
		return new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getField(), bookDTO.getLanguage(), bookDTO.getEdition(), bookDTO.getDescription(), bookDTO.getPublishingCompany(), bookDTO.getState());
	}
}
