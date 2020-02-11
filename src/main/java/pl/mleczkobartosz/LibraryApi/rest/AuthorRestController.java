package pl.mleczkobartosz.LibraryApi.rest;

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
    public List<Author> getAllAuthors(@RequestParam(value = "firstName",required = false) String firstName, @RequestParam(value = "lastName",required = false) String lastName){
        if(firstName!=null && lastName !=null)
        return authorRepository.findAuthorsByFirstNameAndLastName(firstName,lastName);

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

    @PostMapping("/authors")
    public boolean saveAuthor(@RequestBody Author author){
        authorRepository.save(author);
        return true;
    }

    @PutMapping("/authors/{id}")
    public boolean update(@RequestBody Author author,@PathVariable Long id){

        Optional<Author> optional = authorRepository.findById(id);
        Author newAuthor = new Author();
        if(optional.isPresent())
        {
            newAuthor = optional.get();
        }
        else{
            throw new AuthorNotFoundException(id);
        }
        newAuthor.setFirstName(author.getFirstName());
        newAuthor.setLastName(author.getLastName());
        newAuthor.setBirthYear(author.getBirthYear());
        authorRepository.save(newAuthor);
        return true;
    }

    @DeleteMapping("/authors/{id}")
    public boolean delete(@PathVariable Long id){

        Optional<Author> optional = authorRepository.findById(id);
        Author newAuthor = new Author();
        if(optional.isPresent())
        {
            newAuthor = optional.get();
        }
        else{
            throw new AuthorNotFoundException(id);
        }

        authorRepository.delete(newAuthor);
        return true;
    }

}
