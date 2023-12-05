package bibliotroca.BiblioTroca.service;

import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.ReviewNotFoundException;
import bibliotroca.BiblioTroca.exception.TransactionNotFoundException;
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


    public Review createReview (Review review) throws EmailNotFoundException, TransactionNotFoundException  {
        review.setCreateDate(LocalDateTime.now());

        if(this.transactionRepository.findByRegistry(review.getTransactionId()) == null) {
        	throw new TransactionNotFoundException();
        }
        if(this.userRepository.findByEmail(review.getEvaluatedEmail()) == null) {
        	throw new EmailNotFoundException(review.getEvaluatedEmail());
        }
        if(this.userRepository.findByEmail(review.getEvaluatorEmail()) == null) {
        	throw new EmailNotFoundException(review.getEvaluatorEmail());
        }
        
        User user = this.userRepository.findByEmail(review.getEvaluatedEmail());
        if(user.getAvaliationsNumber()==null) {
        	user.setAvaliationsNumber(1);
        }
        user.setAvaliationsNumber(user.getAvaliationsNumber()+1);
        List<Review> reviews = reviewRepository.findByEvaluatedEmail(review.getEvaluatedEmail());
        double totalScore = 0.0;
        int totalReviews = reviews.size();
        for (Review reviewreq : reviews) {
            totalScore += reviewreq.getScore();
        }
        user.setAverageRating(totalScore / totalReviews);
        this.userRepository.save(user);
        
        return reviewRepository.save(review);
    }


    public  Map<String, Object> calculateUserScoreRatings(String evaluatedEmail) {
        List<Review> reviews = reviewRepository.findByEvaluatedEmail(evaluatedEmail);

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
    public Optional<Review> findReviewByTransactionId(Long transactionId) {
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