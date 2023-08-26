package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import entity.Wishlist;
import java.util.Optional;


@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findByNameBook(String nameBook);

}


