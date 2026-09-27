package com.library.backend.catalog.repository;

import com.library.backend.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindBook() {
        Book book = new Book(null, "1984", "George Orwell", "978-0451524935");
        Book savedBook = bookRepository.save(book);

        assertThat(savedBook.getId()).isNotNull();
        
        Book foundBook = bookRepository.findById(savedBook.getId()).orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("1984");
    }
}
