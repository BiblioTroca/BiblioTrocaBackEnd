package bibliotroca.BiblioTroca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
	
	public User() {	}
	
	public User(String id, String cpf, String name, String surname, String email, String telephone, String cep) {
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
	}
	
	public User(String cpf, String name, String surname, String email, String telephone, String cep) {
		this.cpf = cpf;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
	}
	
	public User(String name, String surname, String email, String telephone, String cep) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.cep = cep;
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