package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.LibraryApi.Entity.Author;
import pl.mleczkobartosz.LibraryApi.Entity.Book;
import pl.mleczkobartosz.LibraryApi.exceptions.AuthorNotFoundException;
import pl.mleczkobartosz.LibraryApi.exceptions.BookNotFoundException;
import pl.mleczkobartosz.LibraryApi.repository.AuthorRepository;
import pl.mleczkobartosz.LibraryApi.repository.BookRepository;

import java.util.List;
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
    public List<Book> getAll(@RequestParam(value = "title", required = false) String title){

        if(title!=null&&!title.trim().isEmpty()){
            List<Book> book = bookRepository.findBookByTitle(title);
            return book;
        }

        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book findBookById(Long id){
        Optional<Book> response = bookRepository.findById(id);
        Book book = new Book();
        if(response.isPresent()){
            book = response.get();
        }
        else{
            throw new BookNotFoundException(id);
        }
        return book;
    }

    @PostMapping("/books")
    public boolean saveBook(@RequestBody Book book){

        Optional<Author> optional = authorRepository.findById(book.getAuthor().getAuthor_id());
        Author newAuthor = new Author();
        if(optional.isPresent())
        {
            newAuthor = optional.get();
        }
        else{
            throw new AuthorNotFoundException(book.getAuthor().getAuthor_id());
        }
        book.setAuthor(newAuthor);

        bookRepository.save(book);

        return true;
    }

    @PutMapping("/books/{id}")
    public boolean updateBook(@RequestBody Book book, @PathVariable Long id){

        Optional<Book> response = bookRepository.findById(id);
        Book newBook = new Book();

        if(response.isPresent())
        newBook = response.get();
        else throw new BookNotFoundException(id);

        newBook.setTitle(book.getTitle());
        newBook.setBorrowed(book.isBorrowed());

        bookRepository.save(newBook);
        return true;
    }

    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable Long id){
        Optional<Book> response = bookRepository.findById(id);
        Book newBook = new Book();

        if(response.isPresent())
            newBook = response.get();
        else throw new BookNotFoundException(id);

        bookRepository.delete(newBook);
        return true;
    }

}
