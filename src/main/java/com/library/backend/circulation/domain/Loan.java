package com.library.backend.circulation.domain;

import com.library.backend.catalog.domain.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Member member;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status; // ACTIVE, RETURNED
}
