package bibliotroca.BiblioTroca.dto;

import org.springframework.data.annotation.CreatedDate;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.entity.Wishlist;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Optional;

public class WishlistDTO {

	private String id;
    @NotBlank(message = "Nome do livro é obrigatório")
    private String name;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "Nome do campo de estudo é obrigatório")
    private String category;

    private String user;


    @CreatedDate
    private LocalDateTime createAt;


    public WishlistDTO() {

    }


    public WishlistDTO(String id, String bookName, String author, String studyField, LocalDateTime createDate, String user) {
    	this.id = id;
        this.name = bookName;
        this.author = author;
        this.category = studyField;
        this.createAt = createDate;
        this.user = user;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getCategory() {
        return category;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Wishlist returnWishlist() {
        return new Wishlist(name, author, category, createAt, user);
    }
    
    public static WishlistDTO returnWishlistDTO(Wishlist wishlist) {
        return new WishlistDTO(wishlist.getId(), wishlist.getBookName(), wishlist.getAuthor(), wishlist.getStudyField(), wishlist.getCreateDate(), wishlist.getUser());
    }


	public static Optional<WishlistDTO> returnWishlistDTOOptional(Wishlist wishlist) {
		return Optional.ofNullable(new WishlistDTO(wishlist.getId(), wishlist.getBookName(), wishlist.getAuthor(), wishlist.getStudyField(), wishlist.getCreateDate(), wishlist.getUser()));
	}
}