package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.LibraryApi.Entity.Author;
import pl.mleczkobartosz.LibraryApi.Entity.Book;
import pl.mleczkobartosz.LibraryApi.exceptions.AuthorNotFoundException;
import pl.mleczkobartosz.LibraryApi.exceptions.BookNotFoundException;
import pl.mleczkobartosz.LibraryApi.repository.AuthorRepository;
import pl.mleczkobartosz.LibraryApi.repository.BookRepository;

import java.util.Optional;

@RestController
public class BookRestController {

private final BookRepository bookRepository;
private final AuthorRepository authorRepository;

    public BookRestController(BookRepository bookRepository,AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/books")
    public Page<Book> getAll(@RequestParam Optional<String> title, Pageable pageable){

            return bookRepository.findBookByTitle(title.orElse("_"),pageable);
    }

    @GetMapping("/books/{id}")
    public Book findBookById( @PathVariable Long id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new  BookNotFoundException(id)) ;
        return book;
    }

    @PostMapping("/books")
    public boolean saveBook(@RequestBody Book book){
        Author author = authorRepository.findById(book.getAuthor().getAuthor_id())
                .orElseThrow(()-> new AuthorNotFoundException(book.getAuthor().getAuthor_id()));

        book.setAuthor(author);
        bookRepository.save(book);

        return true;
    }

    @PutMapping("/books/{id}")
    public boolean updateBook(@RequestBody Book book, @PathVariable Long id){

        Book newBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        newBook.setTitle(book.getTitle());
        newBook.setBorrowed(book.isBorrowed());

        bookRepository.save(newBook);
        return true;
    }

    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable Long id){

        Book newBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(newBook);
        return true;
    }

}
