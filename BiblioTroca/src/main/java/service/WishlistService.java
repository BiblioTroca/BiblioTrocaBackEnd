package service;


import entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.WishlistRepository;

import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {

        @Autowired
        WishlistRepository repository;

        public List<Wishlist> searchAll() {
                return repository.findAll();
        }

        public Optional<Wishlist> searchById(Long id) {
                return repository.findById(id);
        }

        public Optional<Wishlist> searchByNameBook(String nameBook) {
                return repository.findByNameBook(nameBook);
        }
         /* public Optional<Wishlist> searchByNameBook(String nameBook) {
            return Optional.empty();
        }*/

        public Optional<Wishlist> save(Wishlist wishlist) {
                return Optional.ofNullable(repository.save(wishlist));
        }


        public Optional<Wishlist> updates(Long id, Wishlist wishlist) {

                Optional<Wishlist> existingWishlist = this.repository.findById(id);

                if (existingWishlist.isPresent()) {
                        Wishlist wishlistToUpdate = existingWishlist.get();
                        wishlistToUpdate.setNameBook(wishlist.getNameBook());

                        return Optional.ofNullable(repository.save(wishlistToUpdate));
                } else {
                        return Optional.empty();
                }
        }


        public void delete(Long id) {
                Optional<Wishlist> wishToDelete = repository.findById(id);
                if (wishToDelete.isPresent()) {
                        repository.delete(wishToDelete.get());
                } else {
                        throw new RuntimeException("Não foi possível excluir o registro");
                }

                }

        }




