package bibliotroca.BiblioTroca.repository;
  
import java.util.Optional;
  
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bibliotroca.BiblioTroca.entity.Point; 
import bibliotroca.BiblioTroca.entity.User;
  
@Repository
public interface PointRepository extends MongoRepository<Point, String> {
  
	  Optional<Point> findById(String id);
	  
	  /* Point updatePoints(String id, int walletPoints, Point point, User user); */
	  
	  Point findByUser(User user);
  
}
 