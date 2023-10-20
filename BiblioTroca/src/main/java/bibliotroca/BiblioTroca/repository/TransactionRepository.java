package bibliotroca.BiblioTroca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

	Optional<Transaction> findById(String id);
		
	Transaction findByTransactionStatus(TransactionStatus transactionStatus);
	
}