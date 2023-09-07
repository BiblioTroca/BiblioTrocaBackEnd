package bibliotroca.BiblioTroca.entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Document(collection="Lista-de-Desejos")
public class Wishlist {

    @Id
    @MongoId
    private String id;
    @NotBlank(message = "Nome do livro é obrigatório")
    private String nameBook;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String category;

    private User user;


    @CreatedDate
    private LocalDateTime createDate;

    public Wishlist(String nameBook, String author, String category, LocalDateTime createDate, User user) {
        this.nameBook = nameBook;
        this.author = author;
        this.category = category;
        this.createDate = createDate;
        this.user = user;
    }

    public Wishlist() {
    }

    public String getId() {
        return id;
    }

    public  void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author= author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category= category;
    }
}
