package bibliotroca.BiblioTroca.service;


import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.entity.User;

import bibliotroca.BiblioTroca.exception.ReviewNotFoundException;
import bibliotroca.BiblioTroca.exception.UserNotFoundException;
import bibliotroca.BiblioTroca.repository.ReviewRepository;
import bibliotroca.BiblioTroca.repository.TransactionRepository;
import bibliotroca.BiblioTroca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;


    public Optional<Review> createReview (Review review)  {
        review.setCreateDate(LocalDateTime.now());

        Optional<Transaction> transactionOptional = transactionRepository.findById(review.getTransactionId());
        /*if (transactionOptional.isEmpty()) {
            throw new TransactionNotFoundException("Transação não encontrada");
        }*/
        Transaction transaction = transactionOptional.get();

        Optional<User> evaluatorOptional = (userRepository.findById(review.getNameEvaluator()));
        Optional<User> evaluatedOptional = (userRepository.findById(review.getNameEvaluated()));

        //.orElseThrow(() -> new UserNotFoundException());
        //  .orElseThrow(() -> new UserNotFoundException());

        User evaluator = evaluatorOptional.get();
        User evaluated = evaluatedOptional.get();

        review.setTransactionId(transaction.getId());
        review.setNameEvaluated(evaluated.getName());
        review.setNameEvaluator(evaluator.getName());
        review.setUserIdEvaluated(evaluated.getId());

        return Optional.ofNullable(reviewRepository.save(review));
    }


    public  Map<String, Object> calculateUserScoreRatings(String userIdEvaluated) {
        List<Review> reviews = reviewRepository.findByUserIdEvaluated(userIdEvaluated);

        if (reviews.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("totalScore", 0.0);
            result.put("totalReviews", 0);
            return result;
        }

        double totalScore = 0.0;
        int totalReviews = reviews.size();

        for (Review review : reviews) {
            totalScore += review.getScore();
        }
       double average = totalScore / totalReviews;

        Map<String, Object> result = new HashMap<>();
        result.put("Nota" , average);
        result.put("Avaliações" , totalReviews);
        return result;

    }


    public List<Review> returnAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> returnReviewById(String id) throws ReviewNotFoundException{
        Optional<Review> reviewFound = reviewRepository.findById(id);
        if (reviewFound.isPresent()) {
            return reviewFound;
        } else {
            throw new ReviewNotFoundException();
        }
    }
    public Optional<Review> findReviewByTransactionId(String transactionId) {
        return reviewRepository.findByTransactionId(transactionId);
    }

    public Optional<Review> updateReview(String id, Review review) {
        Optional<Review> existingReview = this.reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review reviewToUpdate = existingReview.get();
            // reviewToUpdate.setScore(review.getScore());

            return Optional.ofNullable(reviewRepository.save(reviewToUpdate));
        } else {
            return Optional.empty();
        }
    }

    public void deleteReview (String id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        }
    }
}