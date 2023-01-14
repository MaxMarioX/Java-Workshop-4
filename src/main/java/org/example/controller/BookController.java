package org.example.controller;

import org.apache.commons.logging.Log;
import org.example.BookService;
import org.example.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @ResponseBody
    @GetMapping("")
    public List<Book> getList()
    {
        return bookService.getBook();
    }

    @ResponseBody
    @GetMapping("/find")
    public Book findBookById(@RequestParam Long id)
    {
        return bookService.findBook(id);
    }

    @ResponseBody
    @GetMapping("/find/{id}")
    public Book findBookByIdV2(@PathVariable Long id)
    {
        return this.bookService.getBookV2(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        });
    }

    /*
    curl -X POST -i -H "Content-Type: application/json" -d '{"isbn":"34321","title":"Thinking in Java", "publisher":"Helion","type":"programming", "author":"Bruce Eckel"}' http://localhost:8080/books
     */
    @PostMapping("")
    public void addBook(@RequestBody Book book)
    {
        bookService.AddBook(book);
    }

    /*
    curl -X PUT -i -H "Content-Type: application/json" -d '{"id":2,"isbn":"32222","title":"KOCHAM C++",  "publisher":"IT Books","type":"programming", "author":"Bruce Eckel"}' http://localhost:8080/books
     */
    @PutMapping("")
    public void updateBook(@RequestBody Book book)
    {
        bookService.updateBook(book);
    }
    /*
    curl -X DELETE -i  http://localhost:8080/books/1
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
    }
}
