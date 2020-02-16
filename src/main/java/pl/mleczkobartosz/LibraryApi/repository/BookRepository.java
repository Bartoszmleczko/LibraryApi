package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.LibraryApi.Entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    public Page<Book> findAll(Pageable pageable);

    public Page<Book> findBookByTitle(String title, Pageable pageable);

}
