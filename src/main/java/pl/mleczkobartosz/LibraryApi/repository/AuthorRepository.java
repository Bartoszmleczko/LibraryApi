package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.LibraryApi.Entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    public Page<Author> findAll(Pageable pageable);

    public Page<Author> findAuthorsByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);



}
