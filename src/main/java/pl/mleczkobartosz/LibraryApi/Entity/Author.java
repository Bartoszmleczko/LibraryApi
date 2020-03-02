package pl.mleczkobartosz.LibraryApi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long author_id;

    @NotNull(message = "This field must be filled")
    @NotEmpty(message = "This field must be filled")
    @Size(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2)
    @NotNull(message = "This field must be filled")
    @NotEmpty(message = "This field must be filled")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "This field must be filled")
    @Column(name = "birth_year")
    private int birthYear;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){

        if(books.contains(book)){
           throw new IllegalArgumentException("Book already assigned to that Author");
        }

        books.add(book);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void removeBook(Book book){
        books.remove(book);
    }


}
