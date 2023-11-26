package bibliotroca.BiblioTroca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.strategy.State;

public class BookDTO {
	@JsonInclude(Include.NON_NULL)
	private String id;
	private String name;
	private String author;
	private String category;
	private String language;
	private String year;
	private String description;
	private String shortDescription;
	private String publishingCompany;
	private String state;
	@JsonInclude(Include.NON_NULL)
	private UserDTO user;
	private String createdAt;
	
	public BookDTO( ) {	}
	
	public BookDTO(String newTitle, String nweAuthor, String newField, String newLanguage, String newEdition, String newDescription, String newPublishingCompany, State newState, String createdAt) {
		this.name = newTitle;
		this.author = nweAuthor;
		this.category = newField;
		this.language = newLanguage;
		this.year = newEdition;
		this.description = newDescription;
		this.shortDescription = newDescription.substring(0, 0);
		this.publishingCompany = newPublishingCompany;
		this.state = newState.toString();
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setRegistry(Long registry) {
		this.id = registry.toString();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public static BookDTO returnBookDTO(Book book) {
		return new BookDTO(book.getTitle(), book.getAuthor(), book.getField(), book.getLanguage(), book.getEdition(), book.getDescription(), book.getPublishingCompany(), book.getState(), book.getCreatedAt().toString());
	}
	
	public static Book returnBook(BookDTO bookDTO) {
		return new Book(bookDTO.getName(), bookDTO.getAuthor(), bookDTO.getCategory(), bookDTO.getLanguage(), bookDTO.getYear(), bookDTO.getDescription(), bookDTO.getPublishingCompany(), State.getByState(bookDTO.getState()), bookDTO.getCreatedAt());
	}
}
