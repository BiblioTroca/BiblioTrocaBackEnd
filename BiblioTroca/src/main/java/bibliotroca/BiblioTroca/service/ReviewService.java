package bibliotroca.BiblioTroca.service;


import bibliotroca.BiblioTroca.entity.Review;
import bibliotroca.BiblioTroca.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;


    public Optional<Review> createReview (Review review) {
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
            reviewToUpdate.setScore(review.getScore());

            return Optional.ofNullable(reviewRepository.save(reviewToUpdate));
        } else {
            return Optional.empty();
        }
    }
}