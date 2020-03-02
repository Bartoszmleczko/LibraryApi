package pl.mleczkobartosz.LibraryApi.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.mleczkobartosz.LibraryApi.Entity.Author;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="book")
public class Book {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @NotNull(message = "Title can't be null")
    @Column(name = "title")
    private String title;


    @Column(name="is_borrowed")
    private boolean isBorrowed;

    @NotNull(message = "Author can't be null")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn
    @JsonBackReference
    private Author author;

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }


}
