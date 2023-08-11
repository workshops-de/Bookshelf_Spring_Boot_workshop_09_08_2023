package de.workshops.bookshelf.book;

import java.util.Objects;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private String description;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(isbn, book.isbn) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, author, title, description);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
