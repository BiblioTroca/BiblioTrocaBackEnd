package bibliotroca.BiblioTroca.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection="Avaliacao")
public class Review {

    @Id
    private String id;

    //@NotBlank(message = "Nome do avaliador é obrigatório")
    private String nameEvaluator;

    //@NotBlank(message = "Nome do avaliado é obrigatório")
    private String nameEvaluated;

    @NotNull(message = "Pontuação necessária")
    private int score;

    @CreatedDate
    private LocalDateTime createDate;

    private String transactionId;

    private String userIdEvaluated;


    public Review() {
    }

    public Review(String nameEvaluator, String nameEvaluated, int score) {
        this.nameEvaluator = nameEvaluator;
        this.nameEvaluated = nameEvaluated;
        this.score = score;
    }

    public Review(String nameEvaluator, String nameEvaluated, int score, LocalDateTime createDate, String transactionId) {
        this.nameEvaluator = nameEvaluator;
        this.nameEvaluated = nameEvaluated;
        this.score = score;
        this.createDate = createDate;
        this.transactionId = transactionId;
    }

    public Review(String userIdEvaluated, String nameEvaluator, String nameEvaluated, int score, LocalDateTime createDate, String transactionId) {
        this.userIdEvaluated = userIdEvaluated;
        this.nameEvaluator = nameEvaluator;
        this.nameEvaluated = nameEvaluated;
        this.score = score;
        this.createDate = createDate;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEvaluator() {
        return nameEvaluator;
    }

    public void setNameEvaluator(String nameEvaluator) {
        this.nameEvaluator = nameEvaluator;
    }

    public String getNameEvaluated() {
        return nameEvaluated;
    }

    public void setNameEvaluated(String nameEvaluated) {
        this.nameEvaluated = nameEvaluated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserIdEvaluated(String userIdEvaluated) {
        this.userIdEvaluated = userIdEvaluated;
    }

    public String getUserIdEvaluated() {
        return userIdEvaluated;
    }
}



