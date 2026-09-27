package com.library.backend.circulation.controller;

import com.library.backend.circulation.domain.Loan;
import com.library.backend.circulation.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping
    public ResponseEntity<Loan> issueLoan(@RequestParam Long bookId, @RequestParam Long memberId) {
        return new ResponseEntity<>(loanService.issueLoan(bookId, memberId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnLoan(id));
    }
}
