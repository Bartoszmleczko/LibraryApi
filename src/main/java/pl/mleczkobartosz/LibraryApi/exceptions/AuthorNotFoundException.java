package pl.mleczkobartosz.LibraryApi.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super("Author with id " + id + " does not exist");
    }
}
