package com.library.review.controller;

import com.library.review.domain.Review;
import com.library.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testCreateAndGetReviews() {
        Review review = new Review(null, 1L, 1L, 5, "Great book!");
        ResponseEntity<Review> postResponse = restTemplate.postForEntity("/api/reviews", review, Review.class);
        
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody().getId()).isNotNull();

        ResponseEntity<Review[]> getResponse = restTemplate.getForEntity("/api/reviews/book/1", Review[].class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).hasSize(1);
        assertThat(getResponse.getBody()[0].getComment()).isEqualTo("Great book!");
    }
}
