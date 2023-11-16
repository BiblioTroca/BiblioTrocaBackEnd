package bibliotroca.BiblioTroca.service;

import java.util.ArrayList;
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
    	transaction.setStartDate(transaction.getCurrentDate());
    	transaction.setTransactionStatus(TransactionStatus.PENDING);
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> returnAllTransactions() {
		return this.transactionRepository.findAll();
	}
    
    public List<Transaction> returnUserTransactions(String cpf) {
    	List<Transaction> transactions = new ArrayList<>();
    	List<Transaction> sellerTransactions = this.transactionRepository.findAllBySellerCpf(cpf);
    	List<Transaction> buyerTransactions = this.transactionRepository.findAllByBuyerCpf(cpf);
    	for(Transaction transaction : sellerTransactions ) {
    		transactions.add(transaction);
    	}
    	for(Transaction transaction : buyerTransactions ) {
    		transactions.add(transaction);
    	}
		return transactions;
	}
    
    public Transaction returnTransactionByRegistry(Long registry) throws TransactionNotFoundException {
    	if(!this.transactionRepository.existsByRegistry(registry)) {
    		throw new TransactionNotFoundException();
    	}
    	return this.transactionRepository.findByRegistry(registry);
    }
    
    public List<Transaction> returnByTransactionStatus(String transactionStatus) throws TransactionNotFoundException {
		return this.transactionRepository.findAllByTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus)); 
    }
    
    public Transaction updateTransaction(Long registry, Transaction transaction, String transactionStatus) throws TransactionNotFoundException, InsuficientPointsException, TransactionAlreadyClosedException {
    	if(!this.transactionRepository.existsByRegistry(registry)) {
			throw new TransactionNotFoundException();
		}
    	Transaction transactionRequest = this.transactionRepository.findByRegistry(registry);
    	if(transactionRequest != null) {
        	transaction.setRegistry(transactionRequest.getRegistry());
    	}
    	if(!transaction.getCompletionDate().isEmpty()) {
    		throw new TransactionAlreadyClosedException();
    	}
    	transaction.setTransactionStatus(TransactionStatus.getByTransactionStatus(transactionStatus));
    	if(transaction.getTransactionStatus() == TransactionStatus.CONCLUDED) {
    		this.pointService.addPoints(20, transaction.getSellerCpf());
    		this.pointService.deducePoints(20, transaction.getBuyerCpf());
    		transaction.setCompletionDate(transaction.getCurrentDate());
    	}
    	if(transaction.getTransactionStatus() == TransactionStatus.CANCELLED) {
    		transaction.setCompletionDate(transaction.getCurrentDate());
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
		if(returnAllTransactions().isEmpty()) {
			return (long) 1;
		}
		return (long) returnAllTransactions().get(returnAllTransactions().size()-1).getRegistry() + 1;
	}
}