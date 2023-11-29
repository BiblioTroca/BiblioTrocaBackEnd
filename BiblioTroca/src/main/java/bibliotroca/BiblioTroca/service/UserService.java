package bibliotroca.BiblioTroca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookService bookService;
	
	public User createUser(User user) throws CpfAlreadyInUseException {
		if(this.userRepository.existsByCpf(user.getCpf())) {
			throw new CpfAlreadyInUseException();
		}
		return this.userRepository.save(user);
	}
	
	public User createUserLogin(User user) {
		if(this.userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email já cadastrado.");
		}
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
		if(user.getBooksRegistry() == null) {
			user.setBooksRegistry(userRequest.getBooksRegistry());
		}
		if(user.getBooks() == null) {
			user.setBooks(userRequest.getBooks());
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
	
	public User addBook(String cpf, Long registry) throws CpfNotFoundException {
		User userRequest = returnUserByCPF(cpf);
		List<Long> booksRegistryRequest = new ArrayList<>();
		if(userRequest.getBooksRegistry()!=null) {
			booksRegistryRequest = userRequest.getBooksRegistry();
		}
		booksRegistryRequest.add(registry);
		userRequest.setBooksRegistry(booksRegistryRequest);
		return updateUser(cpf, userRequest);
	}
	
	public User returnUserBooks(String cpf) throws CpfNotFoundException, RegistryNotFoundException {
		User userRequest = returnUserByCPF(cpf);
		List<Long> booksRegistryRequest = new ArrayList<>();
		if(userRequest.getBooksRegistry()!=null) {
			booksRegistryRequest = userRequest.getBooksRegistry();
		}
		ArrayList<Book> books = new ArrayList<>();
		for(Long bookRegistry : booksRegistryRequest) {
			if(this.bookService.existsByRegistry(bookRegistry)) {
				books.add(this.bookService.returnBookByRegistry(bookRegistry));
			}
		}
		userRequest.setBooks(books);
		return userRequest;
	}

	public boolean existsByEmail(String email) {
		if(this.userRepository.existsByEmail(email)) {
			return true;
		}
		return false;
	}

	public User returnUserByEmail(String email) {
		if(this.userRepository.existsByEmail(email)) {
			return this.userRepository.findByEmail(email);
		}
		throw new RuntimeException("email não econtrado");
	}

	public Boolean verifyCredentials(User user) {
		User userRequest = returnUserByEmail(user.getEmail());
		if(userRequest.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
	
	public User updateUserMobile(User user) {
		if(!this.userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email não encontrado");
		}
		User userRequest = this.userRepository.findByEmail(user.getEmail());
		user.setId(userRequest.getId());
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
		if(user.getPassword() == null) {
			user.setPassword(userRequest.getPassword());
		}
		return this.userRepository.save(user);
	}
}
