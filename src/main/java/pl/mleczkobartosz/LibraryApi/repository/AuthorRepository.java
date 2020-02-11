package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.LibraryApi.Entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    public List<Author> findAuthorsByFirstNameAndLastName(String firstName, String lastName);



}
