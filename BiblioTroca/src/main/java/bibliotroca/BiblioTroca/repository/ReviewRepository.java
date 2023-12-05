package bibliotroca.BiblioTroca.repository;

import bibliotroca.BiblioTroca.entity.Review;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

	Optional<Review> findByTransactionId(Long transactionId);

	List<Review> findByEvaluatedEmail(String evaluatedEmail);
    
}
