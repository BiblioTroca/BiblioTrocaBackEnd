package bibliotroca.BiblioTroca.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.strategy.TransactionStatus;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

	Transaction findByRegistry(Long registry);

	boolean existsByRegistry(Long registry);

	List<Transaction> findAllByTransactionStatus(TransactionStatus byTransactionStatus);

	void deleteByRegistry(Long registry);

	List<Transaction> findAllBySellerEmail(String email);

	List<Transaction> findAllByBuyerEmail(String email);
	
}