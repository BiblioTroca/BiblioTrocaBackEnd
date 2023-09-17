package bibliotroca.BiblioTroca.dto;

import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.entity.Wishlist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class ReviewDTO {


    @NotBlank(message = "Nome do avaliador é obrigatório")
    private String nameEvaluator;

    @NotBlank(message = "Nome do avaliado é obrigatório")
    private String nameEvaluated;

    @NotNull(message = "Pontuação necessária")
    private int score;

    @CreatedDate
    private LocalDateTime createDate;

    private String transactionId;


    public ReviewDTO() {
    }

    public ReviewDTO(String nameEvaluator, String nameEvaluated, int score, LocalDateTime createDate, String transactionId) {
        this.nameEvaluator = nameEvaluator;
        this.nameEvaluated = nameEvaluated;
        this.score = score;
        this.createDate = createDate;
        this.transactionId = transactionId;
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

    public Review returnReview(ReviewDTO reviewDTO) {
        return new Review(reviewDTO.getNameEvaluator(), reviewDTO.getNameEvaluated(), reviewDTO.getScore(), reviewDTO.getCreateDate(), reviewDTO.getTransactionId());
    }
}
