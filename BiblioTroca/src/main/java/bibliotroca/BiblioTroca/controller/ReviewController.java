package bibliotroca.BiblioTroca.controller;

import bibliotroca.BiblioTroca.dto.ReviewDTO;
import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.exception.BookNotFoundException;
import bibliotroca.BiblioTroca.exception.ReviewAlreadyExists;
import bibliotroca.BiblioTroca.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/bibliotroca/avaliacoes")
public class ReviewController {
        @Autowired
        ReviewService reviewService;
        Review review;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody @Valid ReviewDTO reviewDTO, BindingResult result) throws ReviewAlreadyExists {
        this.review = new Review();
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        } else {
            try {
                int score = reviewDTO.getScore();
                if (score < 0 || score > 5) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pontuação inválida.");
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(this.reviewService.createReview(reviewDTO.returnReview(reviewDTO)));
            } catch (Exception var4) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Review>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.reviewService.returnAllReviews());
    }

    @CrossOrigin
    @GetMapping({"/{id}"})
    public ResponseEntity<Object> returnReviewById (@PathVariable String id){
        Optional<Review> reviewFound = this.reviewService.returnReviewById(id);
        return reviewFound.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(reviewFound.get()) : this.reviewIsEmpty(reviewFound);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReviewById(@PathVariable("id") String id, @RequestBody @Valid ReviewDTO reviewDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        } else {
            Optional<Review> reviewUpdate = reviewService.returnReviewById(id);
            if (reviewUpdate.isPresent()) {
                Optional<Review> updateReview = reviewService.updateReview(id, reviewDTO.returnReview(reviewDTO));
                return ResponseEntity.status(HttpStatus.OK).body(updateReview);
            } else {
                return reviewIsEmpty(reviewUpdate);
            }
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) throws BookNotFoundException {
        Optional<Review> review1 = reviewService.returnReviewById(id);
        if (review1.isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.status(HttpStatus.OK).body("Avaliação excluída");
        }
        throw new BookNotFoundException();
    }


    public ResponseEntity<Object> reviewIsEmpty (Optional < Review > review) {
        return review.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.") : ResponseEntity.status(HttpStatus.OK).body(review.get());
    }

}
