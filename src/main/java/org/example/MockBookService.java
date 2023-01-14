package org.example;

import org.example.entity.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MockBookService implements BookService{

    List<Book> books;
    private static Long nextId = 0L;

    public MockBookService()
    {
        books = new ArrayList<>();

        Book book;

        book = new Book("9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming");
        book.setId(nextId++);
        books.add(book);

        book = new Book("9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion", "programming");
        book.setId(nextId++);
        books.add(book);

        book = new Book("9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion", "programming");
        book.setId(nextId++);
        books.add(book);
    }
    @Override
    public List<Book> getBook() {
        return books;
    }

    @Override
    public Optional<Book> getBookV2(Long id)
    {
        return books.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public Book findBook(Long id)
    {
        for(Iterator<Book> bookIterator = books.iterator(); bookIterator.hasNext();)
        {
            Book book = bookIterator.next();

            if(book.getId().equals(id))
                return book;
        }

        return null;
    }

    public void AddBook(Book book)
    {
        book.setId(nextId++);
        books.add(book);
    }

    public void updateBook(Book book)
    {
        int ptr = 0;
        for(Iterator<Book> bookIterator = books.iterator(); bookIterator.hasNext();ptr++)
        {
            Book bookToUpdate = bookIterator.next();

            if(bookToUpdate.getId().equals(book.getId()))
            {
                bookToUpdate.setAuthor(book.getAuthor());
                bookToUpdate.setIsbn(book.getIsbn());
                bookToUpdate.setPublisher(book.getPublisher());
                bookToUpdate.setTitle(book.getTitle());
                bookToUpdate.setType(book.getType());

                books.set(ptr,bookToUpdate);
            }
        }
    }

    public void updateBookV2(Book book)
    {
        if (this.getBookV2(book.getId()).isPresent()) {

            int indexOf = books.indexOf(this.getBookV2(book.getId()).get());

            books.set(indexOf, book);

        }
    }
    @Override
    public void deleteBook(Long id) {

        if (getBookV2(id).isPresent()) {

            books.remove(this.getBookV2(id).get());

        }
    }
}
