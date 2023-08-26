package service;


import entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.WishlistRepository;

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

        public Optional<Wishlist> searchByNameBook(String nameBook) {
                return repository.findByNameBook(nameBook);
        }
         /* public Optional<Wishlist> searchByNameBook(String nameBook) {
            return Optional.empty();
        }*/

        public Optional<Wishlist> save(Wishlist wishlist) {
                wishlist.setCreateDate(LocalDateTime.now()); // Não é necessário definir manualmente
                return Optional.ofNullable(repository.save(wishlist));
        }


        public Optional<Wishlist> updates(String id, Wishlist wishlist) {
                //public Optional<Wishlist> update(Long id, Wishlist updatedWishlist) {


                Optional<Wishlist> existingWishlist = this.repository.findById(id);

                if (existingWishlist.isPresent()) {
                        Wishlist wishlistToUpdate = existingWishlist.get();
                        wishlistToUpdate.setNameBook(wishlist.getNameBook());
                        wishlistToUpdate.setAuthor(wishlist.getAuthor());
                        wishlistToUpdate.setCategory(wishlist.getCategory());
                       // wishlistToUpdate.setUser(wishlist.getUser());

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




