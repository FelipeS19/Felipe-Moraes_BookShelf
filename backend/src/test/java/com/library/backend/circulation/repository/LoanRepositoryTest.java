package com.library.backend.circulation.repository;

import com.library.backend.catalog.domain.Book;
import com.library.backend.catalog.repository.BookRepository;
import com.library.backend.circulation.domain.Loan;
import com.library.backend.circulation.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testCreateAndFindLoan() {
        // Arrange
        Book book = new Book(null, "Test Book", "Author", "123456");
        book = bookRepository.save(book);

        Member member = new Member(null, "Test Member", "test@test.com");
        member = memberRepository.save(member);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDate.now());
        loan.setStatus("ACTIVE");

        // Act
        Loan savedLoan = loanRepository.save(loan);
        Loan foundLoan = loanRepository.findById(savedLoan.getId()).orElse(null);

        // Assert
        assertThat(foundLoan).isNotNull();
        assertThat(foundLoan.getBook().getTitle()).isEqualTo("Test Book");
        assertThat(foundLoan.getMember().getName()).isEqualTo("Test Member");
        assertThat(foundLoan.getStatus()).isEqualTo("ACTIVE");
    }
}
