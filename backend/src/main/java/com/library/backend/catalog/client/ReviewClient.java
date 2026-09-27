package com.library.backend.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "review-service", url = "http://localhost:8081/api/reviews")
public interface ReviewClient {
    
    @GetMapping("/book/{bookId}")
    List<Map<String, Object>> getReviewsByBook(@PathVariable("bookId") Long bookId);

    @PostMapping
    Map<String, Object> createReview(@RequestBody Map<String, Object> review);
}
