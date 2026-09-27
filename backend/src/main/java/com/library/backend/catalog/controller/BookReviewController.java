package com.library.backend.catalog.controller;

import com.library.backend.catalog.client.ReviewClient;
import com.library.backend.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books/{bookId}/reviews")
public class BookReviewController {

    private final ReviewClient reviewClient;
    private final RabbitTemplate rabbitTemplate;

    public BookReviewController(ReviewClient reviewClient, RabbitTemplate rabbitTemplate) {
        this.reviewClient = reviewClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getBookReviews(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewClient.getReviewsByBook(bookId));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addReview(@PathVariable Long bookId, @RequestBody Map<String, Object> reviewData) {
        reviewData.put("bookId", bookId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, reviewData);
        return ResponseEntity.accepted().body(Map.of("message", "Review submission accepted and is being processed asynchronously via RabbitMQ"));
    }
}
