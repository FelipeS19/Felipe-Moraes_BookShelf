package com.library.backend.circulation.service;

import com.library.backend.catalog.domain.Book;
import com.library.backend.catalog.repository.BookRepository;
import com.library.backend.circulation.domain.Loan;
import com.library.backend.circulation.domain.Member;
import com.library.backend.circulation.repository.LoanRepository;
import com.library.backend.circulation.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@SuppressWarnings("null")
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan issueLoan(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);
    }
}
