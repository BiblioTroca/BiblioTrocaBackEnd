package bibliotroca.BiblioTroca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.Wishlist;
import bibliotroca.BiblioTroca.repository.WishlistRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {

        @Autowired
        WishlistRepository repository;

        public List<Wishlist> searchAll() {
                return repository.findAll();
        }

        public Optional<Wishlist> searchById(String id) {
                return repository.findById(id);
        }

        public Optional<Wishlist> searchByBookName(String bookName) {
                return repository.findByBookName(bookName);
        }
         /* public Optional<Wishlist> searchByNameBook(String bookName) {
            return Optional.empty();
        }*/

        public Optional<Wishlist> save(Wishlist wishlist) {
                wishlist.setCreateDate(LocalDateTime.now()); // Não é necessário definir manualmente
                return Optional.ofNullable(repository.save(wishlist));
        }


        public Optional<Wishlist> updates(String id, Wishlist wishlist) {

                Optional<Wishlist> existingWishlist = this.repository.findById(id);

                if (existingWishlist.isPresent()) {
                        Wishlist wishlistToUpdate = existingWishlist.get();
                        wishlistToUpdate.setBookName(wishlist.getBookName());
                        wishlistToUpdate.setAuthor(wishlist.getAuthor());
                        wishlistToUpdate.setStudyField(wishlist.getStudyField());

                        return Optional.ofNullable(repository.save(wishlistToUpdate));
                } else {
                        return Optional.empty();
                }
        }


        public void delete(String id) {
                Optional<Wishlist> wishToDelete = repository.findById(id);
                if (wishToDelete.isPresent()) {
                        repository.delete(wishToDelete.get());
                } else {
                        throw new RuntimeException("Não foi possível excluir o registro");
                }

                }

        }
