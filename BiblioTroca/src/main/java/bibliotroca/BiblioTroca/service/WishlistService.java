package bibliotroca.BiblioTroca.service;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.BookAlreadyRegisteredException;
import bibliotroca.BiblioTroca.exception.BookNotFoundException;
import bibliotroca.BiblioTroca.exception.UserNotFoundException;
import bibliotroca.BiblioTroca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.Wishlist;
import bibliotroca.BiblioTroca.repository.WishlistRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {
        @Autowired
        WishlistRepository repository;

        @Autowired
        UserRepository userRepository;

        public List<Wishlist> searchAll() {
        	List<Wishlist> wishlist = repository.findAll();
        	Collections.sort(wishlist, (o1, o2) -> (o1.getCreateDate().compareTo(o2.getCreateDate())));
                return wishlist;
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

        public Wishlist saveWishlist(Wishlist wishlist, String cpf) throws  BookAlreadyRegisteredException, UserNotFoundException{
                wishlist.setCreateDate(LocalDateTime.now());
                Optional<Wishlist> existingWishlist = repository.findByBookName(wishlist.getBookName());
                if (existingWishlist.isPresent()) {
                        throw new BookAlreadyRegisteredException();
                }
                Optional<User> user = Optional.ofNullable(userRepository.findByCpf(cpf));

                if (user.isPresent()) {
                        wishlist.setUser(user.get().getName());
                        return repository.save(wishlist);
                }
                else {
                        throw new UserNotFoundException();
                }
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
