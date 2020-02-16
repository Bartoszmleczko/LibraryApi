package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
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
    public Page<Author> getAllAuthors(@RequestParam(value = "firstName",required = false) String firstName, @RequestParam(value = "lastName",required = false) String lastName, Pageable pageable){
        if(firstName!=null && lastName !=null)
        return authorRepository.findAuthorsByFirstNameAndLastName(firstName,lastName,pageable);

        return authorRepository.findAll(pageable);
    }

    @GetMapping("/authors/{id}")
    public Author findById(@PathVariable Long id){

        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        return author;
    }

    @PostMapping("/authors")
    public boolean saveAuthor(@RequestBody Author author){
        authorRepository.save(author);
        return true;
    }

    @PutMapping("/authors/{id}")
    public boolean update(@RequestBody Author author, @PathVariable Long id){

        Author newAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        newAuthor.setFirstName(author.getFirstName());
        newAuthor.setLastName(author.getLastName());
        newAuthor.setBirthYear(author.getBirthYear());
        authorRepository.save(newAuthor);
        return true;
    }

    @DeleteMapping("/authors/{id}")
    public boolean delete(@PathVariable Long id){

        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        authorRepository.delete(author);
        return true;
    }

}
