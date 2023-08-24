package entity;

import javax.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Entity
@Document(collection="Lista-de-Desejos")

public class Wishlist {

    @Id
    @MongoId
    private String id;
    @NotBlank(message = "Nome do livro é obrigatório")
    private String nameBook;

    @NotBlank(message = "Nome do usuário é obrigatório")
    private String user;

    public Wishlist(String nameBook, String user) {
        this.nameBook = nameBook;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

