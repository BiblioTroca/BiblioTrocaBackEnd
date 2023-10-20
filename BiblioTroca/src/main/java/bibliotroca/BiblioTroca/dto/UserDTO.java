package bibliotroca.BiblioTroca.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.User;

public class UserDTO {
	@JsonInclude(Include.NON_NULL)
	private String cpf;
	private String name;
	private String surname;
	private String email;
	private String telephone;
	private String cep;
	@JsonInclude(Include.NON_NULL)
	private List<BookDTO> books;
	
	public UserDTO() {	}
	
	public UserDTO(String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.telephone = newTelephone;
		this.cep = newCep;
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
	
	public List<BookDTO> getBooks() {
		return this.books;
	}
	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	
	public static UserDTO returnUserDTO(User user) {
		return new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), user.getCep());
	}
	
	public static User returnUser(UserDTO userDTO) {
		return new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getTelephone(), userDTO.getCep());
	}
}
