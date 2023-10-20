package bibliotroca.BiblioTroca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;

@Service
public class UserBooksService {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
	public void addUserBook(String cpf, Long registry) throws CpfNotFoundException {
		List<Long> userBooks = new ArrayList<>();
		User user = this.userService.returnUserByCPF(cpf);
		if(user.getBooksRegistry()!=null) {
			for(Long userBooksRequest : user.getBooksRegistry()) {
				userBooks.add(userBooksRequest);
			}
		}
		userBooks.add(registry);
		user.setBooksRegistry(userBooks);
		this.userService.updateUser(cpf, user);
	}
	
	public void removeUserBook(String cpf, Long registry) throws CpfNotFoundException {
		List<Long> userBooks = new ArrayList<>();
		User user = this.userService.returnUserByCPF(cpf);
		if(user.getBooksRegistry()!=null) {
			userBooks = user.getBooksRegistry();
			userBooks.remove(registry);
		}
		user.setBooksRegistry(userBooks);
		this.userService.updateUser(cpf, user);
	}
}
