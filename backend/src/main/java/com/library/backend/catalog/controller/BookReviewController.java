package com.library.backend.catalog.controller;

import com.library.backend.catalog.client.ReviewClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books/{bookId}/reviews")
public class BookReviewController {

    private final ReviewClient reviewClient;

    public BookReviewController(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getBookReviews(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewClient.getReviewsByBook(bookId));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addReview(@PathVariable Long bookId, @RequestBody Map<String, Object> reviewData) {
        reviewData.put("bookId", bookId);
        return ResponseEntity.ok(reviewClient.createReview(reviewData));
    }
}
