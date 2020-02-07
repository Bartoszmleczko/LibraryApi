package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.LibraryApi.Entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
