package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.LibraryApi.Entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {

    @Query("select b from Book b where title like %?1%")
    public Page<Book> findBookByTitle(String title, Pageable pageable);



}
