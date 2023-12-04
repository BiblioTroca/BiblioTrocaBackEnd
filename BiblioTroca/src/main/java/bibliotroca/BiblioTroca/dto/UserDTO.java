package bibliotroca.BiblioTroca.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import bibliotroca.BiblioTroca.entity.User;

public class UserDTO {
	private String id;
	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private String location;
	@JsonInclude(Include.NON_NULL)
	private List<BookDTO> books;
	@JsonInclude(Include.NON_NULL)
	private Number averageRating;
	@JsonInclude(Include.NON_NULL)
	private Number avaliationsNumber;
	@JsonInclude(Include.NON_NULL)
	private String password;
	
	public UserDTO() {	}
	
	public UserDTO(String id, String name, String surname, String email, String telephone, String cep, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = telephone;
		this.location = cep;
		this.password = password;
	}
	
	public UserDTO(String id, String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.id = id;
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.phoneNumber = newTelephone;
		this.location = newCep;
	}
	
	public UserDTO(String id, String newName, String newSurname, String newEmail, String newTelephone, String newCep, Integer avaliationsNumber, Double averageRating, String password) {
		this.id = id;
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.phoneNumber = newTelephone;
		this.location = newCep;
		this.avaliationsNumber = avaliationsNumber;
		this.averageRating = averageRating;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static UserDTO returnUserDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), user.getCep(), user.getAvaliationsNumber(), user.getAverageRating(), user.getPassword());
	}
	
	public static User returnUser(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(), userDTO.getLocation(), userDTO.getAvaliationsNumber().intValue(), userDTO.getAverageRating().doubleValue(), userDTO.getPassword());
	}
}
