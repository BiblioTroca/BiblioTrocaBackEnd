package bibliotroca.BiblioTroca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.ArrayList;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.EmailAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookService bookService;
	
	public User createUser(User user) throws EmailAlreadyInUseException {
		if(this.userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyInUseException();
		}
		return this.userRepository.save(user);
	}
	
	public User createUserLogin(User user) {
		if(this.userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email já cadastrado.");
		}
		return this.userRepository.save(user);
	}
	
	public User createUserMobile(User user) {
		if(this.userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email já cadastrado.");
		}
		user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
		return this.userRepository.save(user);
	}
	
	public List<User> returnAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User returnUserByEmail(String email) throws EmailNotFoundException {
		if(this.userRepository.existsByEmail(email)) {
			return this.userRepository.findByEmail(email);
		}
		throw new EmailNotFoundException(email);
	}
	
	public User updateUser(String email, User user) throws EmailNotFoundException {
		if(!this.userRepository.existsByEmail(email)) {
			throw new EmailNotFoundException(email);
		}
		User userRequest = this.userRepository.findByEmail(email);
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
		if(user.getBooksRegistry() == null) {
			user.setBooksRegistry(userRequest.getBooksRegistry());
		}
		if(user.getBooks() == null) {
			user.setBooks(userRequest.getBooks());
		}
		return this.userRepository.save(user);
	}
	
	public void deleteUser(String email) throws EmailNotFoundException {
		if(this.userRepository.existsByEmail(email)) {
			this.userRepository.deleteByEmail(email);
		} else {
			throw new EmailNotFoundException(email);
		}
	}
	
	public User addBook(String email, Long registry) throws EmailNotFoundException {
		User userRequest = returnUserByEmail(email);
		List<Long> booksRegistryRequest = new ArrayList<>();
		if(userRequest.getBooksRegistry()!=null) {
			booksRegistryRequest = userRequest.getBooksRegistry();
		}
		booksRegistryRequest.add(registry);
		userRequest.setBooksRegistry(booksRegistryRequest);
		return updateUser(email, userRequest);
	}
	
	public User returnUserBooks(String email) throws EmailNotFoundException, RegistryNotFoundException {
		User userRequest = returnUserByEmail(email);
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

	public Boolean verifyCredentials(User user) throws EmailNotFoundException {
		User userRequest = returnUserByEmail(user.getEmail());
		if(BCrypt.verifyer().verify(user.getPassword().toCharArray(), userRequest.getPassword()).verified) {
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
		} else {
			user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
		}
		return this.userRepository.save(user);
	}
}
