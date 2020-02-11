package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.LibraryApi.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    public List<Book> findBookByTitle(String title);

}
