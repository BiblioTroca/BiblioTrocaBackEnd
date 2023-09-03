package bibliotroca.BiblioTroca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		return this.userRepository.save(user);
	}
	
	public List<User> returnAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User returnUserByCPF(String cpf) throws CpfNotFoundException {
		if(this.userRepository.existsByCpf(cpf)) {
			return this.userRepository.findByCpf(cpf);
		}
		throw new CpfNotFoundException(cpf);
	}
	
	public User updateUser(String cpf, User user) throws CpfNotFoundException {
		if(!this.userRepository.existsByCpf(cpf)) {
			throw new CpfNotFoundException(cpf);
		}
		User userRequest = this.userRepository.findByCpf(cpf);
		user.setId(userRequest.getId());
		user.setCpf(cpf);
		if(user.getName() == null) {
			user.setName(userRequest.getName());
		}
		if(user.getSurname() == null) {
			user.setSurname(userRequest.getSurname());
		}
		if(user.getEmail() == null) {
			user.setEmail(userRequest.getEmail());
		}
		if(user.getTelephone() == null) {
			user.setTelephone(userRequest.getTelephone());
		}
		if(user.getCep() == null) {
			user.setCep(userRequest.getCep());
		}
		return this.userRepository.save(user);
	}
	
	public void deleteUser(String cpf) throws CpfNotFoundException {
		if(this.userRepository.existsByCpf(cpf)) {
			this.userRepository.deleteByCpf(cpf);
		} else {
			throw new CpfNotFoundException(cpf);
		}
	}
}
