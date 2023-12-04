package bibliotroca.BiblioTroca.repository;
  
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import bibliotroca.BiblioTroca.entity.Point; 
  
@Repository
public interface PointRepository extends MongoRepository<Point, String> {
  
	Optional<Point> findById(String id);	  

	boolean existsByUserEmail(String email);

	Point findByUserEmail(String email);
  
}
 