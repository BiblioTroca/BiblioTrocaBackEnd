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
    private String bookName;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "Nome do campo de estudo é obrigatório")
    private String studyField;

    private String user;


    @CreatedDate
    private LocalDateTime createDate;

    public Wishlist(String bookName, String author, String studyField, LocalDateTime createDate, String user) {
        this.bookName = bookName;
        this.author = author;
        this.studyField = studyField;
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
    public String getStudyField() {
        return studyField;
    }
    public void setStudyField(String studyField) {
        this.studyField= studyField;
    }
}
