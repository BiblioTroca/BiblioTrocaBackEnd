package bibliotroca.BiblioTroca.entity;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="Avaliacao")
public class Review {

    @Id
    private String id;

    //@NotBlank(message = "Nome do avaliador é obrigatório")
    private String evaluatorEmail;

    //@NotBlank(message = "Nome do avaliado é obrigatório")
    private String evaluatedEmail;

    @NotNull(message = "Pontuação necessária")
    private int score;

    
    private LocalDateTime createDate;

    private Long transactionId;



    public Review() {
    }

    public Review(String evaluatorEmail, String evaluatedEmail, int score) {
        this.evaluatorEmail = evaluatorEmail;
        this.evaluatedEmail = evaluatedEmail;
        this.score = score;
    }

    public Review(String evaluatorEmail, String evaluatedEmail, int score, LocalDateTime createDate, Long transactionId) {
        this.evaluatorEmail = evaluatorEmail;
        this.evaluatedEmail = evaluatedEmail;
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

    public String getEvaluatorEmail() {
        return evaluatorEmail;
    }

    public void setEvaluatorEmail(String evaluatorEmail) {
        this.evaluatorEmail = evaluatorEmail;
    }

    public String getEvaluatedEmail() {
        return evaluatedEmail;
    }

    public void setEvaluatedEmail(String evaluatedEmail) {
        this.evaluatedEmail = evaluatedEmail;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}