package bibliotroca.BiblioTroca.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import bibliotroca.BiblioTroca.dto.BookDTO;
import bibliotroca.BiblioTroca.dto.TransactionDTO;
import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.exception.TransactionAlreadyClosedException;
import bibliotroca.BiblioTroca.exception.TransactionNotFoundException;
import bibliotroca.BiblioTroca.service.BookService;
import bibliotroca.BiblioTroca.service.TransactionService;
import bibliotroca.BiblioTroca.service.UserService;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/transacoes")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	@Autowired
	BookService bookService;
	@Autowired
	UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionDTO createTransaction(@RequestBody @Valid Transaction transaction) throws CpfNotFoundException, RegistryNotFoundException {
		Transaction createdTransaction = this.transactionService.createTransaction(transaction);
		UserDTO seller = UserDTO.returnUserDTO(this.userService.returnUserByCPF(createdTransaction.getSellerCpf()));
		UserDTO buyer = UserDTO.returnUserDTO(this.userService.returnUserByCPF(createdTransaction.getBuyerCpf()));
		BookDTO book = BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(createdTransaction.getBookRegistry()));
		seller.setCpf(createdTransaction.getSellerCpf());
		buyer.setCpf(createdTransaction.getBuyerCpf());
		book.setRegistry(createdTransaction.getBookRegistry());
		TransactionDTO createdTransactionDTO = TransactionDTO.returnTransactionDTO(createdTransaction);
		createdTransactionDTO.setSeller(seller);
		createdTransactionDTO.setBuyer(buyer);
		createdTransactionDTO.setBook(book);
		return createdTransactionDTO;
	}
	
	@GetMapping
	public List<TransactionDTO> returnAllTransactions() throws CpfNotFoundException, RegistryNotFoundException {
		List<Transaction> transactions = this.transactionService.returnAllTransactions();
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transaction : transactions) {
			TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transaction);
			transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getSellerCpf())));
			transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getBuyerCpf())));
			transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transaction.getBookRegistry())));
			transactionsDTO.add(transactionDTO);
		}
		return transactionsDTO;
	}
	
	@GetMapping("/{registry}")
	public TransactionDTO returnTransactionById(@PathVariable Long registry) throws TransactionNotFoundException, CpfNotFoundException, RegistryNotFoundException {
		Transaction transaction = this.transactionService.returnTransactionByRegistry(registry);
		TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transaction);
		transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getSellerCpf())));
		transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getBuyerCpf())));
		transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transaction.getBookRegistry())));
		return transactionDTO;
	}
	
	@GetMapping("usuario/{cpf}")
	public List<TransactionDTO> returnUserTransactions(@PathVariable String cpf) throws TransactionNotFoundException, CpfNotFoundException, RegistryNotFoundException {
		List<Transaction> transactions = this.transactionService.returnUserTransactions(cpf);
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transaction : transactions) {
			TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transaction);
			transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getSellerCpf())));
			transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getBuyerCpf())));
			transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transaction.getBookRegistry())));
			transactionsDTO.add(transactionDTO);
		}
		return transactionsDTO;
	}
	
	@GetMapping("usuario/{cpf}/status/{transactionStatus}")
	public List<TransactionDTO> returnUserTransactionsByStatus(@PathVariable("cpf") String cpf, @PathVariable("transactionStatus") String status) throws TransactionNotFoundException, CpfNotFoundException, RegistryNotFoundException {
		List<Transaction> transactions = this.transactionService.returnUserTransactionsByStatus(cpf, status);
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transaction : transactions) {
			TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transaction);
			transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getSellerCpf())));
			transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transaction.getBuyerCpf())));
			transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transaction.getBookRegistry())));
			transactionsDTO.add(transactionDTO);
		}
		return transactionsDTO;
	}
	
	@GetMapping("/status/{transactionStatus}")
	public List<TransactionDTO> returnByTransactionStatus(@PathVariable String transactionStatus) throws TransactionNotFoundException, CpfNotFoundException, RegistryNotFoundException {
		List<Transaction> transactions = transactionService.returnByTransactionStatus(transactionStatus);
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transactionRequest : transactions) {
			TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transactionRequest);
			transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transactionRequest.getSellerCpf())));
			transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transactionRequest.getBuyerCpf())));
			transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transactionRequest.getBookRegistry())));
			transactionsDTO.add(transactionDTO);
		}
		return transactionsDTO;
	}
	
	@PutMapping("/{registry}/{status}")
	public TransactionDTO updateTransaction(@PathVariable("registry") String registry, @PathVariable("status") String transactionStatus) throws TransactionNotFoundException, InsuficientPointsException, CpfNotFoundException, RegistryNotFoundException, NumberFormatException, TransactionAlreadyClosedException {
		Transaction transactionRequest = this.transactionService.returnTransactionByRegistry(Long.parseLong(registry));
		Transaction transactionUpdated = this.transactionService.updateTransaction(Long.parseLong(registry), transactionRequest, transactionStatus);
		TransactionDTO transactionDTO = TransactionDTO.returnTransactionDTO(transactionUpdated);
		transactionDTO.setTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus));
		transactionDTO.setSeller(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transactionUpdated.getSellerCpf())));
		transactionDTO.setBuyer(UserDTO.returnUserDTO(this.userService.returnUserByCPF(transactionUpdated.getBuyerCpf())));
		transactionDTO.setBook(BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(transactionUpdated.getBookRegistry())));
		return transactionDTO;
	}
	
	@DeleteMapping("/{registry}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransaction(@PathVariable String registry) throws TransactionNotFoundException {
		this.transactionService.deleteTransaction(Long.parseLong(registry));
	}
	
}