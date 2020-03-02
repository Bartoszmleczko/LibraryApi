package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.LibraryApi.Entity.Book;
import pl.mleczkobartosz.LibraryApi.Entity.Role;
import pl.mleczkobartosz.LibraryApi.Entity.User;
import pl.mleczkobartosz.LibraryApi.exceptions.BookNotFoundException;
import pl.mleczkobartosz.LibraryApi.exceptions.UserNotFoundException;
import pl.mleczkobartosz.LibraryApi.repository.BookRepository;
import pl.mleczkobartosz.LibraryApi.repository.RoleRepository;
import pl.mleczkobartosz.LibraryApi.repository.UserRepository;

import java.util.Optional;


@RestController
public class UserRestController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RoleRepository roleRepository;

    public UserRestController(UserRepository userRepository, BookRepository bookRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public Page<User> findAllUsers(@RequestParam Optional<String> username, Pageable pageable){
        return userRepository.findByUsername(username.orElse("_"),pageable);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() ->new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestParam Long id, @RequestBody User user){

        User dbUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));

        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        return userRepository.save(dbUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@RequestParam Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @PutMapping("/users/{userId}/borrow")
    public User borrowBook(@PathVariable Long userId,@RequestBody Book bookId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Book book = bookRepository.findById(bookId.getId()).orElseThrow(() -> new BookNotFoundException(bookId.getId()));

        book.setBorrowed(true);
        user.getBook().add(book);
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}/return")
    public User returnBook(@PathVariable Long userId,@RequestBody Book bookId){

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Book book = bookRepository.findById(bookId.getId()).orElseThrow(() -> new BookNotFoundException(bookId.getId()));
        if(user.getBook().contains(book)){
            user.getBook().remove(book);
            book.setBorrowed(false);
            bookRepository.save(book);
        }

        return userRepository.save(user);
    }


    @PutMapping("/users/{userId}/grant")
    public String grantAdminRole(@PathVariable Long userId){

        User user = userRepository.findById(userId).orElseThrow(() ->new UserNotFoundException(userId));
        Role role = roleRepository.findByName("ADMIN");

        user.getRole().add(role);
        userRepository.save(user);


        return "Granted admin role";
    }

    @PutMapping("/users/{userId}/degrade")
    public String takeAdminAccess(@PathVariable Long  userId){

        User user = userRepository.findById(userId).orElseThrow(() ->new UserNotFoundException(userId));
        Role role = roleRepository.findByName("ADMIN");

        if(!user.getRole().contains(role))
            return "This user has no admin role";

        user.getRole().remove(role);
        userRepository.save(user);

        return "Taken admin role";

    }


}
