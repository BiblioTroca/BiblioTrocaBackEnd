package bibliotroca.BiblioTroca.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import bibliotroca.BiblioTroca.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

	Book findByRegistry(Long registry);

	boolean existsByRegistry(Long registry);

	void deleteByRegistry(Long registry);

	List<Book> findAllByTitle(String search);

	List<Book> findAllByAuthor(String search);

	List<Book> findAllByField(String search);

	List<Book> findAllByDescription(String search);

}
