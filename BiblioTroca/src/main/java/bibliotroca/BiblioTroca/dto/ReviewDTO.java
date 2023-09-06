package bibliotroca.BiblioTroca.dto;

import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewDTO {


    @NotBlank(message = "Nome do avaliador é obrigatório")
    private String nameEvaluator;

    @NotBlank(message = "Nome do avaliado é obrigatório")
    private String nameEvaluated;

    @NotNull(message = "Pontuação necessária")
    private int score;




    public ReviewDTO() {
    }

    public ReviewDTO(String nameEvaluator, String nameEvaluated, int score) {
        this.nameEvaluator = nameEvaluator;
        this.nameEvaluated = nameEvaluated;
        this.score = score;
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


    public ReviewDTO returnReviewDTO(Review review) {
        return new ReviewDTO(review.getNameEvaluator(), review.getNameEvaluated(), review.getScore());
    }

    public Review returnReview(ReviewDTO reviewDTO) {
        return new Review(reviewDTO.getNameEvaluator(), reviewDTO.getNameEvaluated(), reviewDTO.getScore());
    }
}
