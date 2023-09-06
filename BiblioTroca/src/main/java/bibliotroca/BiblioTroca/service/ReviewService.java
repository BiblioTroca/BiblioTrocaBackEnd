package bibliotroca.BiblioTroca.service;


import bibliotroca.BiblioTroca.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {
    @Autowired
    ReviewRepository  reviewRepository;



}
