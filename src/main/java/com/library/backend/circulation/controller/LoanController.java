package com.library.backend.circulation.controller;

import com.library.backend.circulation.domain.Loan;
import com.library.backend.circulation.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam Long bookId, @RequestParam Long memberId) {
        return new ResponseEntity<>(loanService.createLoan(bookId, memberId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnLoan(id));
    }
}
