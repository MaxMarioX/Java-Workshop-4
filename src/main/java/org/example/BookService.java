package org.example;

import org.apache.commons.logging.Log;
import org.example.entity.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {
    List<Book> getBook();
    Book findBook(Long id);
    void AddBook(Book book);
    Optional<Book> getBookV2(Long id);
    void updateBook(Book book);
    void updateBookV2(Book book);

    void deleteBook(Long id);
}
