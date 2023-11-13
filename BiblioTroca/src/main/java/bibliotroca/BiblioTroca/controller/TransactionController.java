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
	public List<TransactionDTO> returnAllTransactions() {
		List<Transaction> transactions = this.transactionService.returnAllTransactions();
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transaction : transactions) {
			transactionsDTO.add(TransactionDTO.returnTransactionDTO(transaction));
		}
		return transactionsDTO;
	}
	
	@GetMapping("/{registry}")
	public TransactionDTO returnTransactionById(@PathVariable Long registry) throws TransactionNotFoundException {
		return TransactionDTO.returnTransactionDTO(transactionService.returnTransactionByRegistry(registry));
	}
	
	@GetMapping("/{status}")
	public List<TransactionDTO> returnByTransactionStatus(@PathVariable String transactionStatus) throws TransactionNotFoundException {
		List<Transaction> transactions = transactionService.returnByTransactionStatus(transactionStatus);
		List<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transactionRequest : transactions) {
			transactionsDTO.add(TransactionDTO.returnTransactionDTO(transactionRequest));
		}
		return transactionsDTO;
	}
	
	@PutMapping("/{registry}/{status}")
	public TransactionDTO updateTransaction(@PathVariable("registry") String registry, @PathVariable("status") String transactionStatus) throws TransactionNotFoundException, InsuficientPointsException {
		Transaction transactionRequest = this.transactionService.returnTransactionByRegistry(Long.parseLong(transactionStatus));
		Transaction transactionUpdated = this.transactionService.updateTransaction(Long.parseLong(registry), transactionRequest, transactionStatus);
		TransactionDTO transaction = TransactionDTO.returnTransactionDTO(transactionUpdated);
		transaction.setTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus));
		return transaction;
	}
	
	@DeleteMapping("/{registry}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransaction(@PathVariable String registry) throws TransactionNotFoundException {
		this.transactionService.deleteTransaction(Long.parseLong(registry));
	}
	
}