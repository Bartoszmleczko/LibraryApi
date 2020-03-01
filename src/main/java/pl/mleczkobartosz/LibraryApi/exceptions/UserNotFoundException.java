package pl.mleczkobartosz.LibraryApi.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with id " + id + "does not exist");
    }
}
