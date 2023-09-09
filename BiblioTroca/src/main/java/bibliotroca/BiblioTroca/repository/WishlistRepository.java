package bibliotroca.BiblioTroca.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import bibliotroca.BiblioTroca.entity.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

	Optional<Wishlist> findByBookName(String bookName);

}
