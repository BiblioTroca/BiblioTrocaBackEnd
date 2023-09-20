package bibliotroca.BiblioTroca.service;


import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.entity.Transaction;
import bibliotroca.BiblioTroca.entity.User;

import bibliotroca.BiblioTroca.repository.ReviewRepository;
import bibliotroca.BiblioTroca.repository.TransactionRepository;
import bibliotroca.BiblioTroca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

        Optional<User> evaluatorOptional = Optional.ofNullable(userRepository.findByCpf(review.getNameEvaluator()));
        Optional<User> evaluatedOptional = Optional.ofNullable(userRepository.findByCpf(review.getNameEvaluated()));

        //.orElseThrow(() -> new UserNotFoundException());
        //  .orElseThrow(() -> new UserNotFoundException());

        User evaluator = evaluatorOptional.get();
        User evaluated = evaluatedOptional.get();

        review.setTransactionId(transaction.getId());
        review.setNameEvaluated(evaluated.getName());
        review.setNameEvaluator(evaluator.getName());
        return Optional.ofNullable(reviewRepository.save(review));
    }


    public List<Review> returnAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> returnReviewById(String id) {
        return reviewRepository.findById(id);
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