package bibliotroca.BiblioTroca.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

	Optional<Transaction> findById(String id);
	
	Transaction findByTransactionStatus(TransactionStatus transactionStatus);
	
}