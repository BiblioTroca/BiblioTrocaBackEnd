package dto;

import entity.User;

public class UserDTO {
	private String name;
	private String surname;
	private String email;
	private String telephone;
	private String cep;
	
	public UserDTO() {	}
	
	public UserDTO(String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.telephone = newTelephone;
		this.cep = newCep;
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
	
	public static UserDTO returnUserDTO(User user) {
		return new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), user.getCep());
	}
	
	public static User returnUser(UserDTO userDTO) {
		return new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getTelephone(), userDTO.getCep());
	}
}
