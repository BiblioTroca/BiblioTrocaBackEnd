package bibliotroca.BiblioTroca.service;

import bibliotroca.BiblioTroca.exception.BookAlreadyRegisteredException;
import bibliotroca.BiblioTroca.exception.BookNotFoundException;
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

        public Optional<Wishlist> searchById(String id) throws BookNotFoundException {
            Optional<Wishlist> wishFound = repository.findById(id);

            if (wishFound.isPresent()) {
                    return Optional.of(wishFound.get());
            } else {
                    throw new BookNotFoundException();
            }
        }


        public Optional<Wishlist> searchByBookName(String bookName) {
                return repository.findByBookName(bookName);

        }


         /* public Optional<Wishlist> searchByNameBook(String bookName) {
            return Optional.empty();
        }*/

        public Wishlist saveWishlist(Wishlist wishlist) throws  BookAlreadyRegisteredException{
                wishlist.setCreateDate(LocalDateTime.now());
                Optional<Wishlist> existingWishlist = repository.findByBookName(wishlist.getBookName());
                if (existingWishlist.isPresent()) {
                        throw new BookAlreadyRegisteredException();
                }
                return repository.save(wishlist);
        }


        public Wishlist updateWishlist(String id, Wishlist wishlist) throws BookNotFoundException {
                Optional<Wishlist> existingWishlist = repository.findById(id);

                if (existingWishlist.isPresent()) {
                        Wishlist wishlistToUpdate = existingWishlist.get();
                        wishlistToUpdate.setBookName(wishlist.getBookName());
                        wishlistToUpdate.setAuthor(wishlist.getAuthor());
                        wishlistToUpdate.setStudyField(wishlist.getStudyField());

                        return repository.save(wishlistToUpdate);
                } else {
                        throw new BookNotFoundException();
                }
        }



        public void deleteBook (String id) throws BookNotFoundException {
                Optional<Wishlist> wishToDelete = repository.findById(id);
                if (wishToDelete.isPresent()) {
                        repository.delete(wishToDelete.get());
                } else {
                        throw new BookNotFoundException();
                        }
                }


        }
