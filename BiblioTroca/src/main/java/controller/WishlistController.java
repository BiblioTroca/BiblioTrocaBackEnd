package controller;


import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import dto.WishlistDTO;
import entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.WishlistService;

@RestController
@RequestMapping({"/api/v1/bibliotroca/lista-de-desejos"})
public class WishlistController {
    @Autowired
    WishlistService wishlistService;
    Wishlist wish;

    public WishlistController() {
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> saveWishlist(@RequestBody @Valid WishlistDTO wishlistDTO, BindingResult result) {
        this.wish = new Wishlist();
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        } else {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(this.wishlistService.save(wishlistDTO.returnWishlist()));
            } catch (Exception var4) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Wishlist>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.wishlistService.searchAll());
    }

    @CrossOrigin
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        Optional<Wishlist> wishDelete = this.wishlistService.searchById(id);
        if (wishDelete.isPresent()) {
            this.wishlistService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro excluído da lista de desejos");
        } else {
            return this.wishlistIsEmpty(wishDelete);
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWishlist(@PathVariable("id") long id, @RequestBody @Valid WishlistDTO wishlistDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        } else {
            Optional<Wishlist> wishUpdate = wishlistService.searchById(id);
            if (wishUpdate.isPresent()) {
                Optional<Wishlist> updatedWishlist = wishlistService.updates(id, wishlistDTO.returnWishlist());
                return ResponseEntity.status(HttpStatus.OK).body(updatedWishlist);
            } else {
                return wishlistIsEmpty(wishUpdate);
            }
        }
    }





    @CrossOrigin
        @GetMapping({"/{id}"})
        public ResponseEntity<Object> searchById (@PathVariable Long id){
        Optional<Wishlist> wishFound = this.wishlistService.searchById(id);
        return wishFound.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(wishFound.get()) : this.wishlistIsEmpty(wishFound);
    }

        public ResponseEntity<Object> wishlistIsEmpty (Optional < Wishlist > wish) {
        return wish.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.") : ResponseEntity.status(HttpStatus.OK).body(wish.get());
    }
    }

