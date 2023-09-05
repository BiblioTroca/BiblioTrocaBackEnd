package bibliotroca.BiblioTroca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import bibliotroca.BiblioTroca.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

	Book findByRegistry(Long registry);

	boolean existsByRegistry(Long registry);

	void deleteByRegistry(Long registry);

}
