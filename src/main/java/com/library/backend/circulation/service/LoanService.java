package com.library.backend.circulation.service;

import com.library.backend.catalog.repository.BookRepository;
import com.library.backend.circulation.domain.Loan;
import com.library.backend.circulation.repository.LoanRepository;
import com.library.backend.circulation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan createLoan(Long bookId, Long memberId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        var member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDate.now());
        loan.setStatus("ACTIVE");
        return loanRepository.save(loan);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");
        return loanRepository.save(loan);
    }
}
