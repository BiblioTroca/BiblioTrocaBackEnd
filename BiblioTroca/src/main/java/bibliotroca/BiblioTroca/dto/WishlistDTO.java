package bibliotroca.BiblioTroca.dto;

import org.springframework.data.annotation.CreatedDate;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.entity.Wishlist;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class WishlistDTO {

    @NotBlank(message = "Nome do livro é obrigatório")
    private String bookName;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "Nome do campo de estudo é obrigatório")
    private String studyField;

    private String user;


    @CreatedDate
    private LocalDateTime createDate;


    public WishlistDTO() {

    }


    public WishlistDTO(String bookName, String author, String studyField, LocalDateTime createDate, String user) {
        this.bookName = bookName;
        this.author = author;
        this.studyField = studyField;
        this.createDate = createDate;
        this.user = user;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }
    public String getStudyField() {
        return studyField;
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
    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public Wishlist returnWishlist() {
        return new Wishlist(bookName, author, studyField, createDate, user);
    }
}