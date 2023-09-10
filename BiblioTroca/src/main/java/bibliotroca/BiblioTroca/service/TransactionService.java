package bibliotroca.BiblioTroca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.repository.TransactionRepository;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	
	public Transaction createTransaction(Transaction transaction) {
		return this.transactionRepository.save(transaction);
	}
	
	public List<Transaction> returnAllTransactions() {
		return this.transactionRepository.findAll();
	}
	
	public Optional<Transaction> returnTransactionById(String id) {
		return this.transactionRepository.findById(id);
	}
	
	public Transaction returnTransactionByStatus(TransactionStatus transactionStatus) {
		return this.transactionRepository.findByTransactionStatus(transactionStatus);
	}
	
	public Transaction updateTransaction(String id, Transaction transaction) {
		return this.transactionRepository.save(transaction);
	}

}