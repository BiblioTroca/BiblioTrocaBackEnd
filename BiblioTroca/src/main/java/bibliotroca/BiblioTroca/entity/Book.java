package bibliotroca.BiblioTroca.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import bibliotroca.BiblioTroca.strategy.State;
import jakarta.validation.constraints.NotBlank;

@Document(collection="Livros")
public class Book {
	@Id
	private String id;
	private Long registry;
	@NotBlank(message="O título é obrigatório")
	private String title;
	@NotBlank(message="O autor é obrigatório")
	private String author;
	@NotBlank(message="A área de estudo é obrigatória")
	private String field;
	@NotBlank(message="O idioma é obrigatório")
	private String language;
	@NotBlank(message="O ano é obrigatório")
	private String edition;
	@NotBlank(message="A descrição é obrigatória")
	private String description;
	@NotBlank(message="A editora é obrigatória")
	private String publishingCompany;
	//@NotBlank(message="A condição do livro é obrigatória")
	private State state;
	@NotBlank(message="O CPF do usuário dono do livro é obrigatório")
	private String userCpf;
	private LocalDateTime createdAt;
	
	public Book() {	}
	
	public Book(Long newRegistry, String newTitle, String newAuthor, String newField, String newLanguage, String newEdition, String newDescription, String newPublishingCompany, State newState, String newUserCpf) {
		this.registry = newRegistry;
		this.title = newTitle;
		this.author = newAuthor;
		this.field = newField;
		this.language = newLanguage;
		this.edition = newEdition;
		this.description = newDescription;
		this.publishingCompany = newPublishingCompany;
		this.state = newState;
		this.userCpf = newUserCpf;
		this.createdAt = LocalDateTime.now();
	}

	public Book(String newTitle, String newAuthor, String newField, String newLanguage, String newEdition, String newDescription, String newPublishingCompany, State newState) {
		this.title = newTitle;
		this.author = newAuthor;
		this.field = newField;
		this.language = newLanguage;
		this.edition = newEdition;
		this.description = newDescription;
		this.publishingCompany = newPublishingCompany;
		this.state = newState;
		this.createdAt = LocalDateTime.now();
	}
	
	public Book(String newTitle, String newAuthor, String newField, String newLanguage, String newEdition, String newDescription, String newPublishingCompany, State newState, String createdAt) {
		this.title = newTitle;
		this.author = newAuthor;
		this.field = newField;
		this.language = newLanguage;
		this.edition = newEdition;
		this.description = newDescription;
		this.publishingCompany = newPublishingCompany;
		this.state = newState;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
