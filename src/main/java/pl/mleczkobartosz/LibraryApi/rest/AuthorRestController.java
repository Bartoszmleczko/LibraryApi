package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mleczkobartosz.LibraryApi.Entity.Author;
import pl.mleczkobartosz.LibraryApi.exceptions.AuthorNotFoundException;
import pl.mleczkobartosz.LibraryApi.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author findById(@PathVariable Long id){
        Optional<Author> optional = authorRepository.findById(id);
        Author author = new Author();
        if(optional.isPresent())
        {
            author = optional.get();
        }else{
            throw new AuthorNotFoundException(id);
        }
        return author;
    }

}
