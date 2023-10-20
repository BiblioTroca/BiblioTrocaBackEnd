package bibliotroca.BiblioTroca.controller;

import java.util.List;
import java.util.Optional;

import bibliotroca.BiblioTroca.exception.BookAlreadyRegisteredException;
import bibliotroca.BiblioTroca.exception.BookNotFoundException;
import bibliotroca.BiblioTroca.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bibliotroca.BiblioTroca.dto.WishlistDTO;
import bibliotroca.BiblioTroca.entity.Wishlist;
import bibliotroca.BiblioTroca.service.WishlistService;
import jakarta.validation.Valid;


@RestController
@RequestMapping({"/api/v1/bibliotroca/desejos"})
public class WishlistController {
    @Autowired
    WishlistService wishlistService;
    Wishlist wish;

    public WishlistController() {
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> saveWishlist(@RequestBody @Valid WishlistDTO wishlistDTO) throws BookAlreadyRegisteredException, UserNotFoundException {
        String bookName = wishlistDTO.getBookName();
        Optional<Wishlist> existingWishlist = wishlistService.searchByBookName(bookName);
        if (existingWishlist.isPresent()) {
            throw new BookAlreadyRegisteredException();
        }
        Wishlist wishlist = wishlistDTO.returnWishlist();
        Optional<Wishlist> savedWishlist = Optional.ofNullable(wishlistService.saveWishlist(wishlist, wishlistDTO.getUser()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWishlist);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Wishlist>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.wishlistService.searchAll());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) throws BookNotFoundException {
        Optional<Wishlist> wishDelete = wishlistService.searchById(id);
        if (wishDelete.isPresent()) {
            wishlistService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro excluído da lista de desejos");
        }
        throw new BookNotFoundException();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWishlist(@PathVariable("id") String id, @RequestBody @Valid WishlistDTO wishlistDTO) throws BookNotFoundException {
        Optional<Wishlist> wishUpdate = wishlistService.searchById(id);
        if (!wishUpdate.isPresent()) {
            throw new BookNotFoundException();
        }
        Optional<Wishlist> updatedWishlist = Optional.ofNullable(wishlistService.updateWishlist(id, wishlistDTO.returnWishlist()));
        return ResponseEntity.status(HttpStatus.OK).body(updatedWishlist);
    }

    @CrossOrigin
        @GetMapping({"/{id}"})
        public ResponseEntity<Object> searchById (@PathVariable String id) throws BookNotFoundException{
        Optional<Wishlist> wishFound = this.wishlistService.searchById(id);
        return wishFound.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(wishFound.get()) : this.wishlistIsEmpty(wishFound);
    }

        public ResponseEntity<Object> wishlistIsEmpty (Optional < Wishlist > wish) {
        return wish.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.") : ResponseEntity.status(HttpStatus.OK).body(wish.get());
        }
    }