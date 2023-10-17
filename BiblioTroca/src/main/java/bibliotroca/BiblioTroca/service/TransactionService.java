package bibliotroca.BiblioTroca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.dto.TransactionDTO;
import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.exception.RegistryNotFoundException;
import bibliotroca.BiblioTroca.exception.TransactionNotFoundException;
import bibliotroca.BiblioTroca.repository.TransactionRepository;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> returnAllTransactions() {
		return this.transactionRepository.findAll();
	}
    
    public Optional<Transaction> returnTransactionById(String id) throws TransactionNotFoundException {
    	if(!this.transactionRepository.existsById(id)) {
    		throw new TransactionNotFoundException();
    	}
    	return this.transactionRepository.findById(id);
    }
    
    public Transaction returnByTransactionStatus(
    		TransactionStatus transactionStatus) throws TransactionNotFoundException {
		return null; 
    }
    
    public Transaction updateTransaction(String id, Transaction transaction, TransactionStatus transactionStatus) 
    		throws TransactionNotFoundException {
    	if(!this.transactionRepository.existsById(id)) {
			throw new TransactionNotFoundException();
		}
    	Optional<Transaction> transactionRequest = this.transactionRepository.findById(id);
    	if(transactionRequest.isPresent() ) {
    		Transaction existingTransaction = transactionRequest.get();
        	transaction.setId(existingTransaction.getId());
    	}
    	transaction.setTransactionStatus(transactionStatus);
    	if(transaction.getBookReceived() == true) {
    		transaction.setTransactionStatus(transactionStatus.CONCLUDED);
    	}
    	if(transaction.getTransactionStatus() == transactionStatus.CONCLUDED) {
    		transaction.setCompletionDate(transaction.getCompletionDate());
    	}
		return this.transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(String id) throws TransactionNotFoundException {
    	if(this.transactionRepository.existsById(id)) {
    		this.transactionRepository.deleteById(id);
    	} else {
    		throw new TransactionNotFoundException();
    	}
    }
    
}