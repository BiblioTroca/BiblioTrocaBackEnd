package bibliotroca.BiblioTroca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.service.TransactionService;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bibliotroca/transacoes")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createTransaction(@RequestBody @Valid Transaction transaction) {
		return this.transactionService.createTransaction(transaction);
	}
	
	@GetMapping
	public List<Transaction> returnAllTransactions() {
		return this.transactionService.returnAllTransactions();
	}
	
	@GetMapping("/{id}")
	public Optional<Transaction> returnTransactionById(@PathVariable String id) {
		return this.transactionService.returnTransactionById(id);
	}
	
	@GetMapping("/{transactionStatus}")
	public Transaction returnTransactionByStatus(@PathVariable TransactionStatus transactionStatus) {
		return this.transactionService.returnTransactionByStatus(transactionStatus);
	}
	
	@PutMapping("/{id}")
	public Transaction updateTransaction(@PathVariable String id, @RequestBody @Valid Transaction transaction) {
		return this.transactionService.updateTransaction(id, transaction);
	}
	
}