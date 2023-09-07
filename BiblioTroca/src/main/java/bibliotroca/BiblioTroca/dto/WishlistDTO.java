package bibliotroca.BiblioTroca.dto;

import org.springframework.data.annotation.CreatedDate;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.entity.Wishlist;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class WishlistDTO {

    @NotBlank(message = "Nome do livro é obrigatório")
    private String nameBook;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String category;

    private User user;


    @CreatedDate
    private LocalDateTime createDate;


    public WishlistDTO() {

    }


    public WishlistDTO(String nameBook, String author, String category, LocalDateTime createDate, User user) {
        this.nameBook = nameBook;
        this.author = author;
        this.category = category;
        this.createDate = createDate;
        this.user = user;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Wishlist returnWishlist() {
        return new Wishlist(nameBook, author, category, createDate, user);
    }
}