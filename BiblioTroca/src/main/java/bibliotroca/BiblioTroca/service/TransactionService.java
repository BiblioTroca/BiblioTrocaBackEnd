package bibliotroca.BiblioTroca.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.exception.TransactionAlreadyClosedException;
import bibliotroca.BiblioTroca.exception.TransactionNotFoundException;
import bibliotroca.BiblioTroca.repository.TransactionRepository;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PointService pointService;
    
    public Transaction createTransaction(Transaction transaction) {
    	transaction.setRegistry(this.generateRegistry());
    	transaction.setStartDate(LocalDateTime.now());
    	transaction.setTransactionStatus(TransactionStatus.PENDING);
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> returnAllTransactions() {
    	List<Transaction> transactionList = this.transactionRepository.findAll();
    	transactionList.removeIf(transaction -> transaction.getStartDate() == null);
		Collections.sort(transactionList, (o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));
		return transactionList;
	}
    
    public List<Transaction> returnUserTransactions(String email) {
    	List<Transaction> transactions = new ArrayList<>();
    	List<Transaction> sellerTransactions = this.transactionRepository.findAllBySellerEmail(email);
    	List<Transaction> buyerTransactions = this.transactionRepository.findAllByBuyerEmail(email);
    	for(Transaction transaction : sellerTransactions ) {
    		transactions.add(transaction);
    	}
    	for(Transaction transaction : buyerTransactions ) {
    		transactions.add(transaction);
    	}
    	transactions.removeIf(transaction -> transaction.getStartDate() == null);
		Collections.sort(transactions, (o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));
		return transactions;
	}
    
    public List<Transaction> returnUserTransactionsByStatus(String email, String status) {
    	List<Transaction> transactions = new ArrayList<>();
    	List<Transaction> userTransactions = this.returnUserTransactions(email);
    	for(Transaction transaction : userTransactions ) {
    		if(transaction.getTransactionStatus().equals(TransactionStatus.getByTransactionStatus(status))) {
    			transactions.add(transaction);
    		}	
    	}
    	transactions.removeIf(transaction -> transaction.getStartDate() == null);
		Collections.sort(transactions, (o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));
		return transactions;
	}
    
    public Transaction returnTransactionByRegistry(Long registry) throws TransactionNotFoundException {
    	if(!this.transactionRepository.existsByRegistry(registry)) {
    		throw new TransactionNotFoundException();
    	}
    	return this.transactionRepository.findByRegistry(registry);
    }
    
    public List<Transaction> returnByTransactionStatus(String transactionStatus) throws TransactionNotFoundException {
    	List<Transaction> transactionList = this.transactionRepository.findAllByTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus));
    	transactionList.removeIf(transaction -> transaction.getStartDate() == null);
		Collections.sort(transactionList, (o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));
		return transactionList;
    }
    
    public Transaction updateTransaction(Long registry, Transaction transaction, String transactionStatus) throws TransactionNotFoundException, InsuficientPointsException, TransactionAlreadyClosedException {
    	if(!this.transactionRepository.existsByRegistry(registry)) {
			throw new TransactionNotFoundException();
		}
    	Transaction transactionRequest = this.transactionRepository.findByRegistry(registry);
    	if(transactionRequest != null) {
        	transaction.setRegistry(transactionRequest.getRegistry());
    	}
    	if(transaction.getCompletionDate() != null) {
    		throw new TransactionAlreadyClosedException();
    	}
    	transaction.setTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus));
    	if(transaction.getTransactionStatus() == TransactionStatus.CONCLUDED) {
    		this.pointService.addPoints(20, transaction.getSellerEmail());
    		this.pointService.deducePoints(20, transaction.getBuyerEmail());
    		transaction.setCompletionDate(LocalDateTime.now());
    	}
    	if(transaction.getTransactionStatus() == TransactionStatus.CANCELLED) {
    		transaction.setCompletionDate(LocalDateTime.now());
    	}
		return this.transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Long registry) throws TransactionNotFoundException {
    	if(this.transactionRepository.existsByRegistry(registry)) {
    		this.transactionRepository.deleteByRegistry(registry);
    	} else {
    		throw new TransactionNotFoundException();
    	}
    }
    
    private Long generateRegistry() {
		if(this.transactionRepository.findAll().isEmpty()) {
			return (long) 1;
		}
		return (long) this.transactionRepository.findAll().get(this.transactionRepository.findAll().size()-1).getRegistry() + 1;
	}
}