package bibliotroca.BiblioTroca.controller;

import bibliotroca.BiblioTroca.dto.ReviewDTO;
import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.ReviewAlreadyExists;
import bibliotroca.BiblioTroca.exception.ReviewNotFoundException;
import bibliotroca.BiblioTroca.exception.TransactionNotFoundException;
import bibliotroca.BiblioTroca.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/bibliotroca/avaliacoes")
public class ReviewController {
        @Autowired
        ReviewService reviewService;
        Review review;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody @Valid Review review) throws ReviewAlreadyExists, ReviewNotFoundException, EmailNotFoundException, TransactionNotFoundException {
        int score = review.getScore();
        if (score < 1 || score > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pontuação inválida.");
        }
        Long transactionId = review.getTransactionId();
        if (transactionId != null) {
            Optional<Review> existingReview = reviewService.findReviewByTransactionId(transactionId);
            if (existingReview.isPresent()) {
                throw new ReviewAlreadyExists();
            }
        }
   
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reviewService.createReview(review));
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Review>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.reviewService.returnAllReviews());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Object> returnReviewById(@PathVariable String id) throws ReviewNotFoundException {
        Optional<Review> reviewFound = this.reviewService.returnReviewById(id);
        if (reviewFound.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(reviewFound.get());
        } else {
            throw new ReviewNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReviewById(@PathVariable("id") String id, @RequestBody @Valid ReviewDTO reviewDTO) throws ReviewNotFoundException {
        Optional<Review> reviewUpdate = reviewService.returnReviewById(id);
        if (!reviewUpdate.isPresent()) {
            throw new ReviewNotFoundException();
        }
        Optional<Review> updateReview = reviewService.updateReview(id, reviewDTO.returnReview(reviewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) throws ReviewNotFoundException {
        Optional<Review> review1 = reviewService.returnReviewById(id);
        if (review1.isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.status(HttpStatus.OK).body("Avaliação excluída");
        } else {
            throw new ReviewNotFoundException();
        }
    }

    @GetMapping("/pontuacao/{userIdEvaluated}")
    public ResponseEntity<Map<String, Object>> getUserScoreRatings(@PathVariable String userIdEvaluated) {
        Map<String, Object> average = reviewService.calculateUserScoreRatings(userIdEvaluated);

        return ResponseEntity.ok(average);
    }

     /*
   @DeleteMapping("/excluir-todos")
    public ResponseEntity<String> excluirTodosDocumentos() {
        reviewService.excluirTodosDocumentos();
        return new ResponseEntity<>("Todos os documentos foram excluídos com sucesso.", HttpStatus.OK);
    }
    public void excluirTodosDocumentos() {
        reviewRepository.deleteAll();
    }
  */

}
