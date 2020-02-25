package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.mleczkobartosz.LibraryApi.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);
    public User findByEmail(String email);

}
