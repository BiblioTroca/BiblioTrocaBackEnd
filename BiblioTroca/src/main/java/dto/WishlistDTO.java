package dto;



import entity.Wishlist;

import javax.validation.constraints.NotBlank;

public class WishlistDTO {

    @NotBlank(message = "Nome do livro é obrigatório")
    private String nameBook;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String user;


    public WishlistDTO() {

    }


    public WishlistDTO(String nameBook, String user) {
        this.nameBook = nameBook;
        this.user = user;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Wishlist returnWishlist() {
        return new Wishlist(nameBook, user);
    }
}
