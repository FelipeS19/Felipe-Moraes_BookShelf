package com.library.review.listener;

import com.library.review.domain.Review;
import com.library.review.repository.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReviewMessageListener {
    private final ReviewRepository reviewRepository;

    public ReviewMessageListener(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RabbitListener(queues = "review.create.queue")
    public void receiveReviewMessage(Map<String, Object> reviewData) {
        try {
            Long bookId = Long.valueOf(reviewData.get("bookId").toString());
            Long memberId = Long.valueOf(reviewData.get("memberId").toString());
            int score = Integer.parseInt(reviewData.get("score").toString());
            String comment = (String) reviewData.get("comment");

            Review review = new Review(null, bookId, memberId, score, comment);
            reviewRepository.save(review);
            System.out.println("Review processed asynchronously for book: " + bookId);
        } catch (Exception e) {
            System.err.println("Error processing review message: " + e.getMessage());
        }
    }
}
