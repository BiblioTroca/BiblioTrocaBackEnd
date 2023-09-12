package bibliotroca.BiblioTroca.repository;

import bibliotroca.BiblioTroca.entity.Review;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {


}
