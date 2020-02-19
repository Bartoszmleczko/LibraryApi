package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.LibraryApi.Entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("select a from Author a where firstName like %?1% and lastName like %?2% and birthYear like ?3")
    public Page<Author> findAuthorsByFirstNameAndLastNameAndBirthYear(String firstName, String lastName, Integer birthYear, Pageable pageable);

    @Query("select a from Author a where firstName like %?1% and lastName like %?2%")
    public Page<Author> findAuthorsByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

}
