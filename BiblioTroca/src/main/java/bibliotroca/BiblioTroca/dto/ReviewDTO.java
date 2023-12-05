package bibliotroca.BiblioTroca.dto;

import bibliotroca.BiblioTroca.entity.Review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReviewDTO {


    @NotBlank(message = "Email do avaliador é obrigatório")
    private String evaluatorEmail;

    @NotBlank(message = "Email do avaliado é obrigatório")
    private String evaluatedEmail;

    @NotNull(message = "Pontuação necessária")
    private int score;

    
    private String createDate;

    private Long transactionId;

    public ReviewDTO() {
    }

    public ReviewDTO(String nameEvaluator, String nameEvaluated, int score, LocalDateTime newcreateDate, Long transactionId) {
        this.evaluatorEmail = nameEvaluator;
        this.evaluatedEmail = nameEvaluated;
        this.score = score;
        this.createDate = newcreateDate != null ? newcreateDate.toString() : null;
        this.transactionId = transactionId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Review returnReview(ReviewDTO reviewDTO) {
        return new Review(reviewDTO.getEvaluatorEmail(), reviewDTO.getEvaluatedEmail(), reviewDTO.getScore(), (reviewDTO.getCreateDate() != null) ? LocalDateTime.parse(reviewDTO.getCreateDate()) : null, reviewDTO.getTransactionId());
    }
    
    public ReviewDTO returnReviewDTO(Review review) {
        return new ReviewDTO(review.getEvaluatorEmail(), review.getEvaluatedEmail(), review.getScore(), review.getCreateDate(), review.getTransactionId());
    }
}
