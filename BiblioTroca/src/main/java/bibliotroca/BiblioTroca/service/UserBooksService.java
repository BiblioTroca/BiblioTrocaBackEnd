package bibliotroca.BiblioTroca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;

@Service
public class UserBooksService {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
	public void addUserBook(String email, Long registry) throws EmailNotFoundException {
		List<Long> userBooks = new ArrayList<>();
		User user = this.userService.returnUserByEmail(email);
		if(user.getBooksRegistry()!=null) {
			for(Long userBooksRequest : user.getBooksRegistry()) {
				userBooks.add(userBooksRequest);
			}
		}
		userBooks.add(registry);
		user.setBooksRegistry(userBooks);
		this.userService.updateUser(email, user);
	}
	
	public void removeUserBook(String email, Long registry) throws EmailNotFoundException {
		List<Long> userBooks = new ArrayList<>();
		User user = this.userService.returnUserByEmail(email);
		if(user.getBooksRegistry()!=null) {
			userBooks = user.getBooksRegistry();
			userBooks.remove(registry);
		}
		user.setBooksRegistry(userBooks);
		this.userService.updateUser(email, user);
	}
}
