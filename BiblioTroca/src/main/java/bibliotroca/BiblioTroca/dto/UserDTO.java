package bibliotroca.BiblioTroca.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.User;

public class UserDTO {
	private String cpf;
	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private String location;
	@JsonInclude(Include.NON_NULL)
	private List<BookDTO> books;
	private Number averageRating;
	private Number avaliationsNumber;
	
	public UserDTO() {	}
	
	public UserDTO(String newCpf, String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.cpf = newCpf;
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.phoneNumber = newTelephone;
		this.location = newCep;
	}
	
	public UserDTO(String newCpf, String newName, String newSurname, String newEmail, String newTelephone, String newCep, Integer avaliationsNumber, Double averageRating) {
		this.cpf = newCpf;
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.phoneNumber = newTelephone;
		this.location = newCep;
		this.avaliationsNumber = avaliationsNumber;
		this.averageRating = averageRating;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<BookDTO> getBooks() {
		return this.books;
	}
	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	
	public Number getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Number averageRating) {
		this.averageRating = averageRating;
	}
	
	public Number getAvaliationsNumber() {
		return avaliationsNumber;
	}
	public void setAvaliationsNumber(Number avaliationsNumber) {
		this.avaliationsNumber = avaliationsNumber;
	}
	
	public static UserDTO returnUserDTO(User user) {
		return new UserDTO(user.getCpf(), user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), user.getCep(), user.getAvaliationsNumber(), user.getAverageRating());
	}
	
	public static User returnUser(UserDTO userDTO) {
		return new User(userDTO.getCpf(), userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(), userDTO.getLocation(), userDTO.getAvaliationsNumber().intValue(), userDTO.getAverageRating().doubleValue());
	}
}
