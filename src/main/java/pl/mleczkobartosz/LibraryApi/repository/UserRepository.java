package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.mleczkobartosz.LibraryApi.Entity.Book;
import pl.mleczkobartosz.LibraryApi.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where username like %?1%")
    public Page<User> findByUsername(String username, Pageable pageable);


    public User findByUsername(String username);
    public User findByEmail(String email);


}
