package entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
@Document(collection="Usuarios")
public class User {
	@Id
	@MongoId
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
	
	public User() {	}
	
	public User(String newCpf, String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.cpf = newCpf;
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.telephone = newTelephone;
		this.cep = newCep;
	}
	
	public User(String newName, String newSurname, String newEmail, String newTelephone, String newCep) {
		this.name = newName;
		this.surname = newSurname;
		this.email = newEmail;
		this.telephone = newTelephone;
		this.cep = newCep;
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
}
