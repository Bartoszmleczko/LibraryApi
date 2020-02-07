package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.LibraryApi.Entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
