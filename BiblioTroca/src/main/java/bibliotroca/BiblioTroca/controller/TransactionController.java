package bibliotroca.BiblioTroca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bibliotroca.BiblioTroca.dto.BookDTO;
import bibliotroca.BiblioTroca.dto.TransactionDTO;
import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
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
	public TransactionDTO createTransaction(@RequestBody @Valid Transaction transaction) 
			throws CpfNotFoundException, RegistryNotFoundException {
		Transaction createdTransaction = this.transactionService.createTransaction(transaction);
		TransactionDTO createdTransactionDTO = TransactionDTO.returnTransactionDTO(createdTransaction);
		UserDTO seller = UserDTO.returnUserDTO(this.userService.returnUserByCPF(createdTransaction.getSeller()));
		UserDTO buyer = UserDTO.returnUserDTO(this.userService.returnUserByCPF(createdTransaction.getBuyer()));
		BookDTO book = BookDTO.returnBookDTO(this.bookService.returnBookByRegistry(createdTransaction.getBook()));
		seller.setCpf(createdTransaction.getSeller());
		buyer.setCpf(createdTransaction.getBuyer());
		book.setRegistry(createdTransaction.getBook());
		createdTransactionDTO.setSeller(seller);
		createdTransactionDTO.setBuyer(buyer);
		createdTransactionDTO.setBook(book);
		return createdTransactionDTO;
	}
	
	@GetMapping
	public ArrayList<TransactionDTO> returnAllTransactions() {
		List<Transaction> transactions = this.transactionService.returnAllTransactions();
		ArrayList<TransactionDTO> transactionsDTO = new ArrayList<>();
		for(Transaction transaction : transactions) {
			transactionsDTO.add(TransactionDTO.returnTransactionDTO(transaction));
		}
		return transactionsDTO;
	}
	
	/*
	@GetMapping("/{id}")
	public TransactionDTO returnTransactionById(@PathVariable String id) throws TransactionNotFoundException {
		TransactionDTO transaction = TransactionDTO.returnTransactionDTO(transactionService.returnTransactionById(id));
		transaction.setId(id);
		return transaction;
	}
	*/
	
	@GetMapping("/{transactionStatus}")
	public TransactionDTO returnByTransactionStatus(
			TransactionStatus transactionStatus) throws TransactionNotFoundException {
		return null;
	}
	
	@PutMapping("/{transactionStatus}")
	public TransactionDTO updateTransaction(
			@PathVariable String id, 
			@PathVariable TransactionStatus transactionStatus,
			@RequestBody @Valid TransactionDTO transactionDTO) throws TransactionNotFoundException {
		Transaction transactionRequest = TransactionDTO.returnTransaction(transactionDTO);
		Transaction transactionUpdated = this.transactionService.updateTransaction(id, transactionRequest, transactionStatus);
		TransactionDTO transaction = TransactionDTO.returnTransactionDTO(transactionUpdated);
		transaction.setTransactionStatus(transactionStatus);
		return transaction;
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransaction(@PathVariable String id) throws TransactionNotFoundException {
		this.transactionService.deleteTransaction(id);
	}
	
}