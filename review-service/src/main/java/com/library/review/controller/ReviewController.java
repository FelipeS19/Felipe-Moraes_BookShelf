package com.library.review.controller;

import com.library.review.domain.Review;
import com.library.review.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);
    }
}
