package bibliotroca.BiblioTroca.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;

@Document(collection="Usuarios")
public class User {
	@Id
	private String id;
	@NotBlank(message="O CPF é obrigatório")
	private String cpf;
	@NotBlank(message="O nome é obrigatório")
	private String name;
	@NotBlank(message="O sobrenome é obrigatório")
	private String surname;
	@NotBlank(message="O email é obrigatório")
	private String email;
	@NotBlank(message="O número de telefone é obrigatório")
	private String telephone;
	@NotBlank(message="O CEP é obrigatório")
	private String cep;
	private Integer avaliationsNumber;
	private Double averageRating;
	private String password;

	// @NotBlank (message="A nota é obrigatória")
	// private String note;

	private List<Long> booksRegistry;
	@JsonInclude(Include.NON_NULL)
	private List<Book> books;
	
	public User() {	}
	
	public User(String id, String name, String surname, String email, String telephone, String cep, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
		this.password = password;
	}
	
	public User(String cpf, String name, String surname, String email, String telephone, String cep) {
		this.cpf = cpf;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
	}
	
	public User(String cpf, String name, String surname, String email, String telephone, String cep, Integer avaliationsNumber, Double averageRating) {
		this.cpf = cpf;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
		this.avaliationsNumber = avaliationsNumber;
		this.averageRating = averageRating;
	}
	
	public User(String name, String surname, String email, String telephone, String cep) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
	}

	public User(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public List<Long> getBooksRegistry() {
		return this.booksRegistry;
	}
	public void setBooksRegistry(List<Long> booksRegistry) {
		this.booksRegistry = booksRegistry;
	}
	
	public List<Book> getBooks() {
		return this.books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public Integer getAvaliationsNumber() {
		return avaliationsNumber;
	}
	public void setAvaliationsNumber(Integer avaliationsNumber) {
		this.avaliationsNumber = avaliationsNumber;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
